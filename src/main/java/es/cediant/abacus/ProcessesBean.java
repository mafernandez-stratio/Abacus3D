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
            
}
