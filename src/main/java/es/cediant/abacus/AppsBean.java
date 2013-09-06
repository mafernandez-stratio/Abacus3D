/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.db.App;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miguel
 */
public class AppsBean implements Serializable {
    
    private static final long serialVersionUID = -1161312516850622268L;

    private List<App> installedApps = new ArrayList<App>();
    private List<App> availableApps = new ArrayList<App>();
    
    public AppsBean() {
        /*
        installedApps.add(new App("Octave", "Available", "octaveLogo.png"));
        installedApps.add(new App("OpenFOAM", "Available", "openFOAMlogo.gif"));
        availableApps.add(new App("Octave", "Available", "octaveLogo.png"));
        availableApps.add(new App("OpenFOAM", "Available", "openFOAMlogo.gif"));
        */
    }

    public List<App> getInstalledApps() {
        return installedApps;
    }

    public void setInstalledApps(List<App> installedApps) {
        this.installedApps = installedApps;
    }

    public List<App> getAvailableApps() {
        return availableApps;
    }

    public void setAvailableApps(List<App> availableApps) {
        this.availableApps = availableApps;
    }        
    
}
