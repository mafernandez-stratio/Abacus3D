
package es.cediant.abacus;

import es.cediant.db.User;
import es.cediant.db.UserHelper;
import es.cediant.encryption.MD5Util;
import es.cediant.util.LdapConnection;
import es.cediant.util.LdapPropHandler;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

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
    private String userId;
    private boolean loggedin = false;   
    private boolean ldapAuthentication = false;
    //private String group;      
    //private String dcs;    
    private ArrayList<String> roles = new ArrayList<String>();
    private LdapConnection ldapc;
    //private String uid;    
    //private String dn;
    //private String ldapAuthType;
    //private List<SelectItem> ldapAuthTypes = new ArrayList<SelectItem>();
    private String activeTab = "Dashboard";
    private String activeSub = "Main";
    //Properties ldapProp;
    private LdapPropHandler lph;
    private long lastAccesedTime;  
    private String logoImg;
    private boolean launchLoading = false;
    private boolean deployLoading = false;
    private boolean testLoading = false;
    private Date startLaunch;
    private Date startDeploy;
    private Date startTest;
        
    //private final String hostLDAP = "10.129.129.148";
    //private final int portLDAP = 389;    
    
    public UserBean(){        
        logoImg = "logoHere.jpg";
        lastAccesedTime = System.currentTimeMillis();
        //System.out.println("New UserBean");
        /*SelectItem item = new SelectItem("CN", "CN"); 
        ldapAuthTypes.add(item);
        item = new SelectItem("UID", "UID"); 
        ldapAuthTypes.add(item);
        ldapProp = new Properties();*/           
        //ldapProp.load(servletContext.getResourceAsStream("/resources/conf/ldap.properties"));        
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }   
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
    
    /*public String getGroup() {
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
    }*/
    
    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }
    
    public void addRole(String newRole){
        roles.add(newRole);
    }

    /*public String getUid() {
    return uid;
    }
    public void setUid(String uid) {
    this.uid = uid;
    }        
    
    public String getDn() {
    return dn;
    }
    public void setDn(String dn) {
    this.dn = dn;
    }
    public String getLdapAuthType() {
    return ldapAuthType;
    }
    public void setLdapAuthType(String ldapAuthType) {
    this.ldapAuthType = ldapAuthType;
    }
    public List<SelectItem> getLdapAuthTypes() {
    return ldapAuthTypes;
    }
    public void setLdapAuthTypes(List<SelectItem> ldapAuthTypes) {
    this.ldapAuthTypes = ldapAuthTypes;
    }*/        
    
    public boolean isLaunchLoading() {
        if(launchLoading){
            Date now = new Date();
            long diff = now.getTime() - startLaunch.getTime();
            System.out.println("diff="+diff);
            if(diff > 5000){
                setLaunchLoading(false);
                startLaunch = null;
            }
        }
        System.out.println("Is launch Loading ? "+launchLoading);
        return launchLoading;
    }
    
    public void setLaunchLoading(boolean launchLoading) {
        if(startLaunch == null){
            startLaunch = new Date();
        }
        System.out.println("New launch Loading = "+launchLoading);
        this.launchLoading = launchLoading;
    }    

    public boolean isDeployLoading() {
        if(deployLoading){
            Date now = new Date();
            long diff = now.getTime() - startDeploy.getTime();
            if(diff > 5000){
                setDeployLoading(false);
                startDeploy = null;
            }
        }
        return deployLoading;
    }

    public void setDeployLoading(boolean deployLoading) {
        if(startDeploy == null){
            startDeploy = new Date();
        }
        this.deployLoading = deployLoading;
    }        

    public boolean isTestLoading() {
        if(testLoading){
            Date now = new Date();
            long diff = now.getTime() - startTest.getTime();
            if(diff > 5000){
                setTestLoading(false);
                startTest = null;
            }
        }
        return testLoading;
    }

    public void setTestLoading(boolean testLoading) {
        if(startTest == null){
            startTest = new Date();
        }
        this.testLoading = testLoading;
    }
            
    public String getActiveTab() {
        //System.out.println("activeTab="+activeTab);
        return activeTab;
    }

    public void setActiveTab(String activeTab) {      
        //System.out.println("<< Active tab = "+activeTab);
        //System.out.println(tabPos);
        String tmp = activeTab;
        if(activeTab.indexOf("Tab") >= 0){
            tmp = activeTab.substring(0, activeTab.indexOf("Tab")+3);
        }
        //System.out.println(tmp);
        //System.out.println(tmp);
        //this.activeTab = activeTab.substring(activeTab.lastIndexOf(":")+1);
        this.activeTab = tmp.substring(tmp.lastIndexOf(":")+1);
        //System.out.println("activeTab = "+getActiveTab());
        //System.out.println(">> Active tab = "+this.activeTab);
    }       

    public String getActiveSub() {
        return activeSub;
    }

    public void setActiveSub(String activeSub) {
        System.out.println("New sub = "+activeSub);
        this.activeSub = activeSub;
    }        
    
    public String changeSub(String newSub){
        System.out.println("New sub="+newSub);
        setActiveSub(newSub);
        return null;
    }        
    
    public void login() {        
        try {            
            //if ((username.compareTo("admin")==0) && (password.compareTo("admin")==0)){ 
            UserHelper uh = new UserHelper();
            MD5Util md5util = new MD5Util();      
            if(ldapAuthentication?loginLDAPwithUID():uh.checkCredentials(username, md5util.encrypt(password))){
            //if(uh.checkCredentials(username, md5util.encrypt(password))){
            //if(uh.checkCredentials(username, password)){
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, 
                                 "Successful login!", 
                                 "Welcome!"));
                setLoggedin(true);                
                //setRoles(new ArrayList<String>(Arrays.asList("admin", "usuario")));
                if(ldapAuthentication){
                    addRole("User");
                    // Check if LDAP user already exists in the User table from MySQL
                    User user = uh.getUser(username);
                    if (user == null){ // Add user to the User table from MySQL
                        uh.addUser(username, md5util.encrypt(password));
                        uh.addRole(username, "User");
                        uh.setCreationDate(username, new Date());
                    }
                }    
                
                /////////////////////////////////////////////////////
                System.out.println("=== ROLES for "+username+" ===");
                ArrayList<String> rolesAdmin = uh.getRoles(username);
                for(String str: rolesAdmin){
                    System.out.println(str);
                }
                System.out.println("==============================");
                /////////////////////////////////////////////////////
                
                setRoles(uh.getRoles(username));
                uh.updateLastConnection(username, new Date());                              
                // Get userId
                User user = uh.getUser(username);
                setUserId(user.getIdUser().toString());     
                System.out.println("userId="+userId); 
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
    
    /*public boolean loginLDAP() {
        if(getLdapAuthType().equalsIgnoreCase("UID")){
            return loginUID();
        }
        boolean result = false;
        try {
            ldapc = new LDAPConnection();
            ldapc.connect(hostLDAP, portLDAP);
            if(!ldapc.isConnected()){
                return false;
            }
            //String passw = "cediant";
            //byte[] passwArray = passw.getBytes();
            //ldapc.bind(com.novell.ldap.LDAPConnection.LDAP_V3, "cn=cediant UAX,ou=Users,dc=abacus,dc=cediant,dc=es", passwArray);
            //ldapc.bind(com.novell.ldap.LDAPConnection.LDAP_V3, username, password.getBytes());
            String cnStr = "cn="+username;
            String ouStr = "ou="+group;
            String[] dcsArray = StringUtil.splitDCs(dcs);
            
//            StringBuilder sb = new StringBuilder();
//            for(int i=0; i<dcsArray.length; i++){
//                sb.append(",dc=");
//                sb.append(dcsArray[i]);
//            }
//            String dcsStr = sb.substring(1);
            
            String dcsStr = StringUtil.formatDCs(dcsArray, ',');
            String dnStr = cnStr+","+ouStr+","+dcsStr;
            //System.out.println("dn: "+dnStr);
            ldapc.bind(LDAPConnection.LDAP_V3, dnStr, password.getBytes());
            if (ldapc.isBound()){
                result = true; 
                // Figure out User Name
                LDAPSearchResults rs = ldapc.search(
                        dnStr, 
                        LDAPConnection.SCOPE_SUB, 
                        null, 
                        null, 
                        Boolean.FALSE);
                LDAPEntry entry = rs.next(); 
                //System.out.println(entry.getAttributeSet().toString());
                //System.out.println(entry.getDN());
                LDAPAttributeSet as=entry.getAttributeSet();
                setUid(as.getAttribute("uid").getStringValue());                
                // Figure out roles
                rs = ldapc.search("ou=Groups,"+dcsStr, LDAPConnection.SCOPE_SUB, 
                                  "cn=*", null, Boolean.FALSE);
                while(rs.hasMore()){ 
                    entry = rs.next();   
                    //System.out.println(entry.toString());
                    //System.out.println(entry.getDN());
                    as=entry.getAttributeSet();
                    LDAPAttribute members = as.getAttribute("memberUid");
                    //System.out.println(members.toString());
                    Enumeration membersList = members.getStringValues();
                    ArrayList<String> membersArray = new ArrayList<String>();
                    membersArray = Collections.list(membersList);                    
                    if(membersArray.contains(getUid())){
                        addRole(entry.getDN().substring(3, entry.getDN().indexOf(",")));
                        //System.out.println(getRoles().toString());
                    }
                    //System.out.println(membersArray.toString());                                     
                }
            }
        } catch (LDAPException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //System.out.println(getRoles().toString());
            return result;
        }
    }*/
    
    /*public boolean loginUID(){
        System.out.println("Login with UID");
        boolean result = false;
        try {
            if(ldapc.isConnected()){
                System.out.println("Previously connected");
            } else {
                ldapc = new LDAPConnection();
                ldapc.connect(hostLDAP, portLDAP); 
            }                             
            if(!ldapc.isConnected()){
                System.out.println("Cannot connect with LDAP");
                return false;
            }            
            if(group.equalsIgnoreCase("")){
                group="Users";
            } else {
                System.out.println("group="+group);
            }
            if(dcs.equalsIgnoreCase("")){
                dcs="abacus cediant es";
            } else {
                System.out.println("dcs="+dcs);
            }
            String[] dcsArray = StringUtil.splitDCs(dcs);
            String dcsStr = StringUtil.formatDCs(dcsArray, ',');
            
            System.out.println("ou="+group+","+dcsStr);
            System.out.println("uid="+username);
                        
            LDAPSearchResults rs = ldapc.search(
                        "ou="+group+","+dcsStr,
                        LDAPConnection.SCOPE_SUB,
                        "uid="+username,
                        null,
                        false); 
            System.out.println("Count(rs)="+rs.getCount());
            if(rs.getCount()<1){
                return false;
            }            
            LDAPEntry entry = rs.next();            
            dn = entry.getDN(); 
            System.out.println("DN="+dn);
            System.out.println("Attributes="+entry.getAttributeSet().toString());
            ldapc.bind(com.novell.ldap.LDAPConnection.LDAP_V3, dn, password.getBytes());
            if (ldapc.isBound()){
                System.out.println("Bound");
                result = true; 
                // Extract UID                   
                setUid(entry.getAttributeSet().getAttribute("uid").getStringValue());
                setUid(username);
                // Figure out roles
                rs = ldapc.search(
                        "ou=Groups,"+dcsStr, 
                        LDAPConnection.SCOPE_SUB, 
                        "cn=*", 
                        null, 
                        Boolean.FALSE);
                while(rs.hasMore()){ 
                    entry = rs.next();  
                    System.out.println("DN="+entry.getDN());
                    LDAPAttributeSet as = entry.getAttributeSet();
                    System.out.println("AS="+as.toString());
                    LDAPAttribute members = as.getAttribute("memberUid");
                    System.out.println("members="+members.getStringValues());
                    Enumeration membersList = members.getStringValues();
                    System.out.println("membersList="+membersList.toString());
                    ArrayList<String> membersArray = new ArrayList<String>();
                    membersArray = Collections.list(membersList); 
                    System.out.println("membersArray="+membersArray.toString());
                    System.out.println("UID="+getUid());
                    if(membersArray.contains(getUid())){
                        addRole(entry.getDN().substring(3, entry.getDN().indexOf(",")));
                        System.out.println("Adding role="+entry.getDN().substring(3, entry.getDN().indexOf(",")));
                    }                                     
                }
            }
        } catch (LDAPException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Roles="+getRoles().toString());
            return result;
        }
    }*/
    
    private boolean loginLDAPwithUID() {      
        try {
            System.out.println("Login LDAP with UID");        
            //lph = new LdapPropHandler(servletContext.getResourceAsStream("/resources/conf/ldap.properties"));
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            Properties ldapProp = new Properties();
            ldapProp.load(servletContext.getResourceAsStream("/resources/conf/ldap.properties"));
            lph = new LdapPropHandler(ldapProp); 
            ldapc = new LdapConnection(lph);      
            /*boolean con = !ldapc.connect();
            System.out.println(con);
            boolean bou = !ldapc.bind();
            System.out.println(bou);
            System.out.println(con || bou);*/
            if((!ldapc.connect()) || (!ldapc.bind())){
                System.out.println("Error");
                return false;
            }
            System.out.println("Connected and bound");  
            return ldapc.authenticate(username, password);
        } catch (IOException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void logout(){        
        setLoggedin(false);
        System.out.println("Loggedin? "+isLoggedin());
        if(ldapAuthentication){
            ldapc.disconnect();
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
    
    public boolean isAdmin(){        
        return roles.contains("Admin");
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
    
    public void pollSession(ActionEvent actionEvent){
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        long newLastAccessedTime = httpSession.getLastAccessedTime();
        long elapsedTime = newLastAccessedTime - lastAccesedTime;
        System.out.println("Elapsed time="+elapsedTime);
        lastAccesedTime = newLastAccessedTime;
    }
    
}
