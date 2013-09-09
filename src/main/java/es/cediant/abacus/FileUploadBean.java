/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.db.App;
import es.cediant.db.AppHelper;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class FileUploadBean implements Serializable {
    
    private static final long serialVersionUID = 3213926339321138917L;
    
    private ArrayList<UploadedScript> files = new ArrayList<UploadedScript>();
    private App selectedType;
    private ArrayList<App> availableTypes;
 
    public void show(OutputStream stream, Object object) {
        try {
            stream.write(getFiles().get((Integer) object).getData());
            stream.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUploadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void listener(FileUploadEvent event) throws Exception {
        UploadedFile item = event.getUploadedFile();
        UploadedScript file = new UploadedScript();
        file.setLength(item.getData().length);
        file.setName(item.getName());
        file.setData(item.getData());
        files.add(file);
    }
 
    public String clearUploadData() {
        System.out.println("Scripts' type: "+selectedType);
        files.clear();
        return null;
    }
    
    public String executeScript() {        
        Include new process in the DB;
        return null;
    }
 
    public int getSize() {
        if (getFiles().size() > 0) {
            return getFiles().size();
        } else {
            return 0;
        }
    }
 
    public long getTimeStamp() {
        return System.currentTimeMillis();
    }
 
    public ArrayList<UploadedScript> getFiles() {
        return files;
    }
 
    public void setFiles(ArrayList<UploadedScript> files) {
        this.files = files;
    }

    public App getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(App selectedType) {
        this.selectedType = selectedType;
    }

    public ArrayList<App> getAvailableTypes() {
        return availableTypes;
    }

    public void setAvailableTypes(ArrayList<App> availableTypes) {
        this.availableTypes = availableTypes;
    }   
    
    public List<App> getInstTypes(){
        AppHelper ah = new AppHelper();
        return ah.getInstApps();
    }
    
}
