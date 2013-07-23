/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.encryption;

import com.novell.ldap.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */
public class MD5Util {
    
    MessageDigest md = null;
    
    public MD5Util(){
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String encrypt(String code){
        try {
            md.update(code.getBytes("UTF8"));
            //byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            /*StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();*/
            return Base64.encode(md.digest());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MD5Util.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }        
    
}
