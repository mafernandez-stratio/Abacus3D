/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */
public class LdapPropReader {
    
    private String host;
    private int port;
    private String cn;
    private String password;
    private String group;
    private String dcs;
    private Properties ldapProp;
    
    public LdapPropReader(InputStream fis){
        try {
            ldapProp = new Properties();
            ldapProp.load(fis);
            host = ldapProp.getProperty("host");
            port = Integer.parseInt(ldapProp.getProperty("port"));            
            cn = ldapProp.getProperty("cn");            
            password = ldapProp.getProperty("password");            
            group = ldapProp.getProperty("group");            
            dcs = ldapProp.getProperty("dcs");
        } catch (IOException ex) {
            Logger.getLogger(LdapPropReader.class.getName()).log(Level.SEVERE, null, ex);
        }
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
}
