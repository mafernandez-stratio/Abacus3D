/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author miguel
 */
@ManagedBean
@SessionScoped
public class OverviewBean implements Serializable {
    
    private static final long serialVersionUID = -4291905383097992691L;

    /**
     * Creates a new instance of OverviewBean
     */
    public OverviewBean() {
    }
    
    public Date getDate() {
        return new Date();
    }
}
