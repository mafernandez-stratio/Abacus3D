/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import java.io.Serializable;

/**
 *
 * @author miguel
 */
public class App implements Serializable {
    
    private static final long serialVersionUID = -6352577646787043369L;
    
    private String name; 
    private String status;    
    private String logo;

    public App(String name) {
        this(name, "Available", "blankLogo.gif");
    }
    
    public App(String name, String status) {
        this(name, status, "blankLogo.gif");
    }
    
    public App(String name, String status, String logo) {
        this.name = name;
        this.status = status;
        this.logo = logo;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }        

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
}
