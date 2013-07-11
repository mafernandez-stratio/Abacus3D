
package es.cediant.abacus;

import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;
import es.cediant.db.UserHelper;
import es.cediant.encryption.MD5Util;
import es.cediant.util.StringUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author miguel
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {  
    
    private static final long serialVersionUID = 4474970058733848377L;
    
    private String username;
    private String password;    
    private boolean loggedin = false;   
    private boolean ldapAuthentication = false;
    private String group;      
    private String dcs;    
    private ArrayList<String> roles = new ArrayList<String>();
    private LDAPConnection ldapc;
    
    private final String hostLDAP = "10.129.129.148";
    private final int portLDAP = 389;
    
    public UserBean() {   
       //System.out.println("New UserBean");
    }
    
    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        /*if (loggedin)
            System.out.println("Loggedin");*/
        this.loggedin = loggedin;
    }
    
    public boolean isLdapAuthentication() {
        return ldapAuthentication;
    }

    public void setLdapAuthentication(boolean ldapAuthentication) {
        this.ldapAuthentication = ldapAuthentication;
    }
    
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    
    public String getDcs() {
        return dcs;
    }

    public void setDcs(String dcs) {
        this.dcs = dcs;
    }
    
    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }
    
    public void login() {
        try {            
            //if ((username.compareTo("admin")==0) && (password.compareTo("admin")==0)){ 
            UserHelper uh = new UserHelper();
            MD5Util md5util = new MD5Util();      
            if(ldapAuthentication?loginLDAP():uh.checkCredentials(username, md5util.encrypt(password))){
            //if(uh.checkCredentials(username, md5util.encrypt(password))){
            //if(uh.checkCredentials(username, password)){
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, 
                                 "Successful login!", 
                                 "Welcome!"));
                setLoggedin(true);                
                setRoles(new ArrayList<String>(
                    Arrays.asList("admin", "usuario")
                ));
                FacesContext.getCurrentInstance().getExternalContext().redirect("/main.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                                 "Wrong login!", 
                                 "Try Again!"));
                setLoggedin(false);
            }            
        } catch (IOException ex) {
            setLoggedin(false);
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public boolean loginLDAP() {
        boolean result = false;
        try {
            ldapc = new LDAPConnection();
            ldapc.connect(hostLDAP, portLDAP);
            //String passw = "cediant";
            //byte[] passwArray = passw.getBytes();
            //ldapc.bind(com.novell.ldap.LDAPConnection.LDAP_V3, "cn=cediant UAX,ou=Users,dc=abacus,dc=cediant,dc=es", passwArray);
            //ldapc.bind(com.novell.ldap.LDAPConnection.LDAP_V3, username, password.getBytes());
            StringBuilder sb = new StringBuilder();
            sb.append("cn=");
            sb.append(username);
            sb.append(",ou=");
            sb.append(group);            
            String[] dcsArray = null;
            dcsArray = StringUtil.splitDCs(dcs);
            for(int i=0; i<dcsArray.length; i++){
                sb.append(",dc=");
                sb.append(dcsArray[i]);
            }
            ldapc.bind(com.novell.ldap.LDAPConnection.LDAP_V3, sb.toString(), password.getBytes());
            if (ldapc.isBound()){
                result = true;
                sb.delete(0, sb.length());
                sb.append("ou=Groups");
                for(int i=0; i<dcsArray.length; i++){
                    sb.append(",dc=");
                    sb.append(dcsArray[i]);
                }
                // Figure out roles
                LDAPSearchResults rs = ldapc.search(
                        sb.toString(), 
			LDAPConnection.SCOPE_SUB, 
                        "(&(objectCategory=person)(objectClass=user))", 
                        null, 
                        Boolean.FALSE);
                while(rs.hasMore()){ 
                    LDAPEntry entry = rs.next();   
                    LDAPAttributeSet as=entry.getAttributeSet();
                    System.out.println(entry.getAttribute("objectClass"));
                    System.out.println(entry.getAttribute("objectCategory"));
                    System.out.println(entry.getDN() +" ");
                    if (entry.getAttribute("objectCategory").getStringValue().equals("CN=Person,CN=Schema,CN=Configuration,DC=AIA,DC=BIZ")) {
                    //System.out.print(entry.getDN() +" ");
                    System.out.println(entry.getAttribute("name").getStringValue());
                    
                    //System.out.print(entry.getAttribute("objectCategory").getStringValue()+" ");    
                    //System.out.println(entry.getAttribute("displayName").getStringValue());   
                }
                
            }            
        } catch (LDAPException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return result;
        }
    }
    
    public void logout(){        
        setLoggedin(false);
        if(ldapAuthentication){
            try {
                ldapc.disconnect();
            } catch (LDAPException ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        redirectToLogin();
    }
    
    public void unregister(){        
        UserHelper uh = new UserHelper();
        uh.removeUser(username);
        setLoggedin(false);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        redirectToLogin();
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }     
 
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }    
    
    public void checkLoggedin() throws IOException {
        //System.out.println("Checking Loggedin...");
        if (isLoggedin()) {
            //System.out.println("Loggedin... Redirecting to main.xhtml");
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/main.xhtml");
        } /*else {
            System.out.println("Not Loggedin... Stay here at login.xhtml");
        }*/
    }
    
    public void checkNotLoggedin() throws IOException {
        //System.out.println("Checking NOT Loggedin...");
        if (!isLoggedin()) {
            //System.out.println("Not Loggedin... Redirecting to login.xhtml");
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
        } /*else {
           System.out.println("Loggedin... Stay here at main.xhtml"); 
        }*/
    }
    
    public void redirectToLogin() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
