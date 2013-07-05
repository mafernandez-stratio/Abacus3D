/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.db.UserHelper;
import es.cediant.encryption.MD5Util;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author miguel
 */
@ManagedBean
@RequestScoped
public class RegistrationBean implements Serializable {   
    
    private static final long serialVersionUID = 3404563846267570951L;
    
    private String newUserName;
    private String newPass;
    private String newPassRep;
    
    public RegistrationBean() {
    }
    
    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getNewPassRep() {
        return newPassRep;
    }

    public void setNewPassRep(String newPassRep) {
        this.newPassRep = newPassRep;
    }    
    
    public void createUser(){
        try {           
            UserHelper uh = new UserHelper();
            if ((newPass.compareTo(newPassRep)!=0)){ 
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                                 "Passwords do not match!", 
                                 "Try Again!"));
            } else if (uh.usernameAlreadyExists(newUserName)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                                 "Username already exists!", 
                                 "Try Again!"));
            } else {
                MD5Util md5util = new MD5Util();
                uh.addUser(newUserName, md5util.encrypt(newPass));
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, 
                                 "New user created!", 
                                 "Welcome!"));
                FacesContext.getCurrentInstance().getExternalContext().redirect("/login.xhtml");                                                                                
            }            
        } catch (IOException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
}
