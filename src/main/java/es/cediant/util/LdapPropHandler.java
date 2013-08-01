/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */
public class LdapPropHandler {
    
    private String host;
    private int port;
    private String cn;
    private String password;
    private String group;
    private String dcs;
    private Properties ldapProp;
    
    public LdapPropHandler(Properties ldapProp){
        try {
            this.ldapProp = ldapProp;
            host = ldapProp.getProperty("host");
            System.out.println("host="+ldapProp.getProperty("host"));
            port = Integer.parseInt(ldapProp.getProperty("port"));            
            cn = ldapProp.getProperty("cn");            
            password = ldapProp.getProperty("password");            
            group = ldapProp.getProperty("group");            
            dcs = ldapProp.getProperty("dcs");                        
        } catch (NumberFormatException ex){
            Logger.getLogger(LdapPropHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // GETTERS
    public Properties getLdapProp() {
        return ldapProp;
    }    
    
    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getCn() {
        return cn;
    }

    public String getPassword() {
        return password;
    }

    public String getGroup() {
        return group;
    }

    public String getDcs() {
        return dcs;
    }    

    // SETTERS
    public void setProperty(String key, String value){
       ldapProp.remove(key);
       ldapProp.setProperty(key, value);
    }
    
    public void setHost(String host) {
        setProperty("host", host);
        this.host = host;
    }

    public void setPort(int port) {
        setProperty("port", Integer.toString(port));
        this.port = port;
    }

    public void setCn(String cn) {
        setProperty("cn", cn);
        this.cn = cn;
    }

    public void setPassword(String password) {
        setProperty("password", password);
        this.password = password;
    }

    public void setGroup(String group) {
        setProperty("group", group);
        this.group = group;
    }

    public void setDcs(String dcs) {
        setProperty("dcs", dcs);
        this.dcs = dcs;
    }        
}
