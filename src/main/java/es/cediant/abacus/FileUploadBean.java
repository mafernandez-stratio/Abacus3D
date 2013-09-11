/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.db.App;
import es.cediant.db.AppHelper;
import es.cediant.db.Process;
import es.cediant.db.ProcessHelper;
import es.cediant.db.User;
import es.cediant.db.UserHelper;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class FileUploadBean implements Serializable {
    
    private static final long serialVersionUID = 3213926339321138917L;
    
    private ArrayList<UploadedScript> files = new ArrayList<UploadedScript>();
    private String selectedType;
    private String selectedPrior;
    private String selectedStart;
    private ArrayList<App> availableTypes;
    private String clearedFile;    
 
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
        //System.out.println("Sending scripts");
        //System.out.println("selectedType: "+selectedType);
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "userBean");
        String username = userBean.getUsername();
        UserHelper uh = new UserHelper();
        ProcessHelper ph = new ProcessHelper();
        for(UploadedScript script: files){
            User user = uh.getUser(username);
            String name = script.getName();
            //System.out.println("Sending: "+name);
            String type = getInstType(Integer.parseInt(selectedType)-1);
            int priority = 5;
            if(selectedPrior!=null){
                priority = Integer.parseInt(selectedPrior);
            }             
            String status = "running";
            Date startTime = new Date();
            if((selectedStart!=null) && (!Boolean.parseBoolean(selectedStart))){
                status = "waiting";
                startTime = null;                
            }
            Date endTime = null;
            Process process = new Process(user, name, type, priority, status, startTime, endTime);            
            ph.addProc(process);
        }
        files.clear();
        selectedType = null;
        selectedPrior = null;
        selectedStart = null;
        return null;
    }
 
    public boolean disableButton(){
        //System.out.println("Disable Button?");
        //System.out.println("files.size="+files.size());
        //System.out.println("null(selectedType)? "+(selectedType==null));
        return ((files.size() < 1) || (selectedType == null));  
    }
    
    public void valueChanged(ValueChangeEvent event) {
        //System.out.println("New Value");
        selectedType = (String) event.getNewValue();     
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

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public String getSelectedPrior() {
        return selectedPrior;
    }

    public void setSelectedPrior(String selectedPrior) {
        this.selectedPrior = selectedPrior;
    }

    public String getSelectedStart() {
        return selectedStart;
    }

    public void setSelectedStart(String selectedStart) {
        this.selectedStart = selectedStart;
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
    
    public String getInstType(int i){
        AppHelper ah = new AppHelper();
        App app = (App) ah.getInstApps().get(i);
        return app.getName();
    }

    public String getClearedFile() {
        return clearedFile;
    }

    public void setClearedFile(String clearedFile) {
        this.clearedFile = clearedFile;
    }
    
    public void clearFile() {
        //System.out.println("Files to clear: " + this.clearedFile);
        Iterator<UploadedScript> i = files.iterator();
        while (i.hasNext()) {
            UploadedScript file = (UploadedScript) i.next();
            //String tmpName = file.getName();
            //System.out.println("tmpName="+tmpName);
            if (file.getName().equals(this.clearedFile)) {
                i.remove();
                break;
            }
        }
    }
    
}
