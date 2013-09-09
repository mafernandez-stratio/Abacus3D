/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.db.ProcessHelper;
import es.cediant.db.Process;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author miguel
 */
public class ProcessesBean implements Serializable {
    
    private static final long serialVersionUID = 5651395627768122272L;
    
    private List<Process> allProcesses;
    private String action;

    /**
     * Creates a new instance of ProcessesBean
     */
    public ProcessesBean() {
    }

    public List<Process> getAllProcesses() {
        ProcessHelper ph = new ProcessHelper();
        allProcesses = ph.listProcesses();
        return allProcesses;
    }

    public void setAllProcesses(List<Process> allProcesses) {
        this.allProcesses = allProcesses;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }        
    
    public boolean isWaiting(int id){
        ProcessHelper ph = new ProcessHelper();
        Process process = ph.getProcess(id);
        return process.getStatus().equalsIgnoreCase("waiting"); 
    }
            
    public boolean isRunning(int id){
        ProcessHelper ph = new ProcessHelper();
        Process process = ph.getProcess(id);
        return process.getStatus().equalsIgnoreCase("running");  
    }
    
    public boolean isFinished(int id){
        ProcessHelper ph = new ProcessHelper();
        Process process = ph.getProcess(id);
        return process.getStatus().equalsIgnoreCase("finished"); 
    }            
    
}
