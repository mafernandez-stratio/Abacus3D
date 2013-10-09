/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

public class FileUploadBean implements Serializable {
    
    private static final long serialVersionUID = 3213926339321138917L;
    
    private ArrayList<UploadedImage> files = new ArrayList<UploadedImage>();
    //private Part uploadedFile;
 
    public void paint(OutputStream stream, Object object) throws IOException {
        stream.write(getFiles().get((Integer) object).getData());
        stream.close();
    }
 
    public void listener(FileUploadEvent event) throws Exception {
        System.out.println("Listening...");
        UploadedFile item = event.getUploadedFile();
        UploadedImage file = new UploadedImage();
        file.setLength(item.getData().length);
        file.setName(item.getName());
        file.setData(item.getData());
        files.add(file);
        System.out.println("DONE!");
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
}
