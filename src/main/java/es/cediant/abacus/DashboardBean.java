/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author miguel
 */
@ManagedBean
@ViewScoped
public class DashboardBean implements Serializable {
    private static final long serialVersionUID = 648864815261749745L;
    private boolean pollEnabled = false;
    private String strOne;
    private String strTwo;

    /**
     * Creates a new instance of DashboardBean
     */
    public DashboardBean() {
    }

    public String getStrOne() {
        throw new Exception("Not implemented yet");
    }

    public void setStrOne(String strOne) {
        this.strOne = strOne;
    }

    public String getStrTwo() {
        throw new Exception("Not implemented yet");
    }

    public void setStrTwo(String strTwo) {
        this.strTwo = strTwo;
    }
    
    
    public boolean getPollEnabled() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserBean userBean = (UserBean) context.getApplication().evaluateExpressionGet(context, "#{userBean}", UserBean.class);
        setPollEnabled(userBean.getActiveTab().equalsIgnoreCase("Dashboard"));
        return pollEnabled;
    }
 
    public void setPollEnabled(boolean pollEnabled) {
        this.pollEnabled = pollEnabled;
    }
    
    public void updateData(){
        throw new Exception("Not implemented yet");
    }
    
}
