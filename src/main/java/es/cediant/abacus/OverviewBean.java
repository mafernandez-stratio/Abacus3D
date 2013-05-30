/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author miguel
 */
@ManagedBean
@RequestScoped
public class OverviewBean implements Serializable {

    /**
     * Creates a new instance of OverviewBean
     */
    public OverviewBean() {
    }
    
    public Date getDate() {
        return new Date();
    }
}
