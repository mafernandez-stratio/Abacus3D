/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author miguel
 */
@ManagedBean
@SessionScoped
public class IndexBean implements Serializable {
    private static final long serialVersionUID = -2636970407579942147L;

    /**
     * Creates a new instance of IndexBean
     */
    public IndexBean() {
    }
    
    private String userName;
    private String password;

    public String getUserName() {
            return userName;
    }

    public void setUserName(String userName) {
            this.userName = userName;
    }

    public String getPassword() {
            return password;
    }

    public void setPassword(String password) {
            this.password = password;
    }
}
