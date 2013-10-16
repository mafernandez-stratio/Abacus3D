/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.db.ProcessHelper;
import es.cediant.db.User;
import es.cediant.db.UserHelper;
import es.cediant.util.ExecThread;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

public class FileUploadBean implements Serializable {
    
    private static final long serialVersionUID = 3213926339321138917L;
    
    private ArrayList<UploadedImage> files = new ArrayList<UploadedImage>();
    private UploadedImage file;
    private String homeDir;
    private String device;
    private String username;
    private String appName;
    private String priority;
    private boolean startNow = false;
    //private Part uploadedFile;
    private String message;
    private String result;
    
    public FileUploadBean(){
        homeDir = System.getProperty("user.home");        
    }
 
    public void paint(OutputStream stream, Object object) throws IOException {
        stream.write(getFiles().get((Integer) object).getData());
        stream.close();
    }
 
    public void listener(FileUploadEvent event) throws Exception {
        UploadedFile item = event.getUploadedFile();   
        file = new UploadedImage();
        file.setLength(item.getData().length);
        file.setName(item.getName());
        file.setData(item.getData());
        files.clear();
        files.add(file);
        
        ///////////////////////////////////////////
        //InputStream stream = item.getInputStream(); 
        
        String prefix = FilenameUtils.getBaseName(item.getName()); 
        System.out.println(prefix);
        String suffix = FilenameUtils.getExtension(item.getName());
        
        //File newFile = File.createTempFile(prefix, "."+suffix, new File(homeDir));
        File newFile = new File(homeDir+"/"+prefix+"."+suffix);
        
        InputStream input = item.getInputStream();
        OutputStream output = new FileOutputStream(newFile);
        
        try {
            IOUtils.copy(input, output);
        } finally {
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(input);
        }
        
        System.out.println("Uploaded file successfully saved in " + newFile.getAbsolutePath());
        ///////////////////////////////////////////
        
        System.out.println(file.getName());
    }
 
    public String clearUploadData() {
        files.clear();
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
 
    public ArrayList<UploadedImage> getFiles() {
        return files;
    }
 
    public void setFiles(ArrayList<UploadedImage> files) {
        this.files = files;
    }
    
    public UploadedImage getFile() {
        return file;
    }

    public void setFile(UploadedImage file) {
        this.file = file;
    }
    
    public boolean launchTest(String app){
        System.out.println("Launching test...");
        try {            
            
            System.out.println("file.getName(): "+file.getName());
            
            this.result = null;
            this.message = null;
            
            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "userBean");
            username = userBean.getUsername();
            
            UserHelper uh = new UserHelper();
            System.out.println("username="+username);
            User user = uh.getUser(username);
            System.out.println("UserID="+user.getIdUser());
            
            if((this.appName==null) || (this.appName.length()<1)){
                this.appName = FilenameUtils.getBaseName(file.getName());
            }
            
            int priorityInt = 5;            
            if((this.priority != null) && this.priority.equalsIgnoreCase("low")){
                priorityInt = 1;
            } else if ((this.priority != null) && this.priority.equalsIgnoreCase("high")){
                priorityInt = 10;
            }                         
            
            String status = "running";
            Date date = new Date();
            if(!this.startNow){
                status = "waiting";
                date = null;
            }
                        
            String octavePath = "/opt/octave/standard/bin/"+app+" --silent "+homeDir+"/"+file.getName();
            
            File f = new File("/opt/octave/standard/bin/"+app);
            
            if((this.device != null) && this.device.equalsIgnoreCase("gpu")){
                octavePath = "/opt/octave/cuda/bin/"+app+" --silent "+homeDir+"/"+file.getName();
                f = new File("/opt/octave/cuda/bin/"+app);
            }            
            
            if(!f.exists()){
                System.out.println("Executable path unavailable");
                this.result = "error";
                this.message = "Executable path unavailable";                
                this.clearForm();
                return false;
            } 
            
            if(file == null){
                System.err.println("Upload a script file");
                this.result = "error";
                this.message = "Upload a script file";                
                this.clearForm();
                return false;
            }
            
            es.cediant.db.Process process = new es.cediant.db.Process(
                    user, 
                    appName, 
                    "test", 
                    priorityInt, 
                    status, 
                    date, 
                    null);
            
            ProcessHelper ph = new ProcessHelper();  
            int idProcess = ph.addProc(process);                                    
                                                            
            if(this.startNow){
                System.out.println("Lacunching process");
                ExecThread execThread = new ExecThread(
                        idProcess, 
                        ph, 
                        octavePath);
                Thread thread = new Thread(execThread);                        
                thread.start();   
            }
            
            //ph.modify(idProcess, "finished");     
                                    
            System.out.println("appName: "+appName);
            System.out.println("priority: "+priority);
            System.out.println("startNow: "+startNow);
            System.out.println("device: "+device);
            
            this.clearForm();
            
            this.result = "OK";
            this.message = "Test launched";
            
        /* 
        } catch (IOException ex) {
            Logger.getLogger(FileUploadBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FileUploadBean.class.getName()).log(Level.SEVERE, null, ex);
        */
        } catch (Exception ex){
            Logger.getLogger(FileUploadBean.class.getName()).log(Level.SEVERE, null, ex);            
            this.result = "error";
            this.message = "Something went wrong"; 
        } finally {
            System.out.println("Test DONE!");
            return true;
        }
    }        
    
    public void clearForm(){
        this.startNow = false;
        this.device = null;
        this.file = null;
        this.files.clear();
        this.appName = null;
        this.priority = null;
    }
    
    public void changeStart(AjaxBehaviorEvent abe){
        this.startNow = !this.startNow;
        System.out.println("New startNow: "+this.startNow);
    }
    
    public void changeStart(){
        this.startNow = !this.startNow;
        System.out.println("New startNow: "+this.startNow);
    }
    
    /*
    public void upload(AjaxBehaviorEvent event){
        System.out.println("call upload...");      
        System.out.println(uploadedFile.getContentType());
        System.out.println(uploadedFile.getName());
        System.out.println(uploadedFile.getSubmittedFileName());
        System.out.println(uploadedFile.getSize());
        try {          
            byte[] results=new byte[(int)uploadedFile.getSize()];
            InputStream in=uploadedFile.getInputStream();
            in.read(results);         
        } catch (IOException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }         
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uploaded!"));
    }
    
    public void upload(){
        System.out.println("call upload...");      
        System.out.println(uploadedFile.getContentType());
        System.out.println(uploadedFile.getName());
        System.out.println(uploadedFile.getSubmittedFileName());
        System.out.println(uploadedFile.getSize());
        try {          
            byte[] results=new byte[(int)uploadedFile.getSize()];
            InputStream in=uploadedFile.getInputStream();
            in.read(results);         
        } catch (IOException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }         
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uploaded!"));
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }        
    */

    public String getHomeDir() {
        return homeDir;
    }

    public void setHomeDir(String homeDir) {
        this.homeDir = homeDir;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        //System.out.println("device: "+device);
        this.device = device;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        //System.out.println("appName: "+appName);
        this.appName = appName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        //System.out.println("priority: "+priority);
        this.priority = priority;
    }

    public boolean isStartNow() {
        return startNow;
    }

    public void setStartNow(boolean startNow) {
        //System.out.println("startNow: "+startNow);
        this.startNow = startNow;
    }       

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
}
