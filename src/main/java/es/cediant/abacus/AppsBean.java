/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.db.App;
import es.cediant.db.AppHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miguel
 */
public class AppsBean implements Serializable {
    
    private static final long serialVersionUID = -1161312516850622268L;

    private List<App> allApps = new ArrayList<App>();
    private List<App> installedApps = new ArrayList<App>();
    private List<App> availableApps = new ArrayList<App>();
    private String action;
    private String newParam;
    
    public AppsBean() {
        /*
        installedApps.add(new App("Octave", "Available", "octaveLogo.png"));
        installedApps.add(new App("OpenFOAM", "Available", "openFOAMlogo.gif"));
        availableApps.add(new App("Octave", "Available", "octaveLogo.png"));
        availableApps.add(new App("OpenFOAM", "Available", "openFOAMlogo.gif"));
        */
        AppHelper ah = new AppHelper();
        allApps = ah.getAllApps();
        installedApps = ah.getInstApps();
        availableApps = ah.getAvailApps();
    }

    public List<App> getAllApps() {
        AppHelper ah = new AppHelper();
        allApps = ah.getAllApps();
        return allApps;
    }

    public void setAllApps(List<App> allApps) {
        this.allApps = allApps;
    }        

    public List<App> getInstalledApps() {
        AppHelper ah = new AppHelper();
        installedApps = ah.getInstApps();
        return installedApps;
    }

    public void setInstalledApps(List<App> installedApps) {
        this.installedApps = installedApps;
    }

    public List<App> getAvailableApps() {   
        AppHelper ah = new AppHelper();
        availableApps = ah.getAvailApps();
        return availableApps;
    }

    public void setAvailableApps(List<App> availableApps) {
        this.availableApps = availableApps;
    }        

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
        System.out.println("Action="+action);
    }        
    
    public boolean isInstalled(String appName){
        System.out.println("isInstalled("+appName+")?");
        for(App app: installedApps){
            System.out.println("Comparing with... "+app.getName());
            if(appName.equalsIgnoreCase(app.getName())){
                return true;
            }
        }
        return false;
    }
    
    public boolean isAvailable(String appName){
        System.out.println("isAvailable("+appName+")?");
        if(isInstalled(appName)){
            return false;
        } else {
            for(App app: availableApps){
                System.out.println("Comparing with... "+app.getName());
                if(appName.equalsIgnoreCase(app.getName())){
                    return true;
                }
            }  
            return false;
        }
    }

    public String getNewParam() {
        return newParam;
    }

    public void setNewParam(String newParam) {
        this.newParam = newParam;
    }        
    
}
