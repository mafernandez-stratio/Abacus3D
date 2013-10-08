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
 
    public void paint(OutputStream stream, Object object) throws IOException {
        stream.write(getFiles().get((Integer) object).getData());
        stream.close();
    }
 
    public void listener(FileUploadEvent event) throws Exception {
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
    
}
