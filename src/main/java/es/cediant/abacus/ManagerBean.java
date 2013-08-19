/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.util.LdapConnection;
import es.cediant.util.LdapPropHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author miguel
 */
@ManagedBean
@SessionScoped
public class ManagerBean implements Serializable {
    
    private static final long serialVersionUID = -7372173437493164616L;
    
    private String host;
    private String port;
    private String cn;
    private String password;
    private String group;      
    private String dcs; 
    LdapPropHandler lpr;
    LdapConnection ldapc;

    /**
     * Creates a new instance of ManagerBean
     */
    public ManagerBean(){
        try {
            //lpr = new LdapPropHandler(servletContext.getResourceAsStream("/resources/conf/ldap.properties"));         
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();            
            Properties ldapProp = new Properties();
            ldapProp.load(servletContext.getResourceAsStream("/resources/conf/ldap.properties"));
            lpr = new LdapPropHandler(ldapProp); 
            host=lpr.getHost();
            port=Integer.toString(lpr.getPort());
            cn=lpr.getCn();
            password=lpr.getPassword();
            group=lpr.getGroup();
            dcs=lpr.getDcs();
        } catch (IOException ex) {
            Logger.getLogger(ManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }        

    public String getGroup(){
        return group;
    }

    public void setGroup(String group){
        this.group = group;
    }

    public String getDcs(){
        return dcs;
    }

    public void setDcs(String dcs){
        this.dcs = dcs;
    }
    
    public void saveLdapInfo(){
        System.out.println("Saving LDAP info...");
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            Properties ldapProp = new Properties();
            ldapProp.load(servletContext.getResourceAsStream("/resources/conf/ldap.properties"));
            LdapPropHandler lph = new LdapPropHandler(ldapProp); 
            lph.setProperty("host", host);
            lph.setProperty("port", port);
            lph.setProperty("cn", cn);
            lph.setProperty("password", password);                        
            lph.setProperty("group", group);
            lph.setProperty("dcs", dcs);
            String absoluteDiskPath = servletContext.getRealPath("/resources/conf/ldap.properties");
            File file = new File(absoluteDiskPath); 
            FileOutputStream out = new FileOutputStream(file);
            lph.getLdapProp().store(out, "ldapProp");
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("LDAP info saved.");
    }
    
}
