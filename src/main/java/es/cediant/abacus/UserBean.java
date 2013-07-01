
package es.cediant.abacus;

import java.io.IOException;
import java.io.Serializable;
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
    
    public void login() {
        try {
            if ((username.compareTo("admin")==0) && (password.compareTo("admin")==0)){ 
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, 
                                 "Successful login!", 
                                 "Welcome!"));
                setLoggedin(true);
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
    
    public void redirectToLogin() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
    }
}
