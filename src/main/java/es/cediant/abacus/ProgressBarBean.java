/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.Serializable;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 
/**
 * @author miguel
 */
@ManagedBean
@ViewScoped
public class ProgressBarBean implements Serializable {
    private boolean buttonRendered = true;
    private boolean enabled = false;
    private int currentValue;
 
    public String startProcess() {
        setEnabled(true);
        setButtonRendered(false);
        setCurrentValue(0);
        return null;
    }
 
    public void increment() {
        if (isEnabled() && currentValue < 100) {
            currentValue += 2;
            if (currentValue >= 100) {
                setButtonRendered(true);
            }
        }
    }
 
    public boolean isEnabled() {
        return enabled;
    }
 
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
 
    public boolean isButtonRendered() {
        return buttonRendered;
    }
 
    public void setButtonRendered(boolean buttonRendered) {
        this.buttonRendered = buttonRendered;
    }
 
    public int getCurrentValue() {
        if (!isEnabled()) {
            return -1;
        }
        return currentValue;
    }
 
    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
 
}

