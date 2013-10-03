/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author miguel
 */
public class CalendarBean implements Serializable {
    private static final long serialVersionUID = 1325505910637899396L;
    private Locale locale;
    private boolean popup;
    private String pattern;
    private Date selectedStartDate;
    private Date selectedEndDate;
    private boolean showApply = true;
    private boolean useCustomDayLabels;
    private boolean disabled = false;

    /**
     * Creates a new instance of CalendarBean
     */
    public CalendarBean() {
        locale = Locale.getDefault();
        popup = true;
        pattern = "d/M/yy HH:mm";
    }
    
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public boolean isPopup() {
        return popup;
    }

    public void setPopup(boolean popup) {
        this.popup = popup;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void selectLocale(ValueChangeEvent event) {
        String tLocale = (String) event.getNewValue();
        if (tLocale != null) {
            String lang = tLocale.substring(0, 2);
            String country = tLocale.substring(3);
            locale = new Locale(lang, country, "");
        }
    }

    public boolean isUseCustomDayLabels() {
        return useCustomDayLabels;
    }

    public void setUseCustomDayLabels(boolean useCustomDayLabels) {
        this.useCustomDayLabels = useCustomDayLabels;
    }

    public Date getSelectedStartDate() {
        return selectedStartDate;
    }

    public void setSelectedDate(Date selectedStartDate) {
        this.selectedStartDate = selectedStartDate;
    }

    public Date getSelectedEndDate() {
        return selectedEndDate;
    }

    public void setSelectedEndDate(Date selectedEndDate) {
        this.selectedEndDate = selectedEndDate;
    }    
    
    public boolean isShowApply() {
        return showApply;
    }

    public void setShowApply(boolean showApply) {
        this.showApply = showApply;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isShowApplyAvailable() {
        return !disabled && popup;
    }

    public boolean isPatternAvailable() {
        return !disabled && popup;
    }
            
}
