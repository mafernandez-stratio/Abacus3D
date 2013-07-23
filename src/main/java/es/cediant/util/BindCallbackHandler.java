/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.util;

import com.novell.security.sasl.RealmCallback;
import com.novell.security.sasl.RealmChoiceCallback;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 *
 * @author miguel
 */
class BindCallbackHandler implements CallbackHandler{

    private char[] m_password;
    
    BindCallbackHandler(String  password){
        m_password = new char[password.length()];
        password.getChars(0, password.length(), m_password, 0);
    }
    
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException{
        for (int i=0; i<callbacks.length; i++){
            if (callbacks[i] instanceof PasswordCallback){
                ((PasswordCallback)callbacks[i]).setPassword(m_password);
            }
            else if (callbacks[i] instanceof NameCallback){
                ((NameCallback)callbacks[i]).setName(
                ((NameCallback)callbacks[i]).getDefaultName());
            }
            else if(callbacks[i] instanceof RealmCallback){
                ((RealmCallback)callbacks[i]).setText(
                ((RealmCallback)callbacks[i]).getDefaultText());
            }
            else if (callbacks[i] instanceof RealmChoiceCallback){
                ((RealmChoiceCallback)callbacks[i]).setSelectedIndex(0);

            }

        }

    }
    
}
