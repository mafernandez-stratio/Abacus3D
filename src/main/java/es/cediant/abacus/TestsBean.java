/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.Serializable;
import es.cediant.db.Process;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miguel
 */
public class TestsBean implements Serializable {
    
    private static final long serialVersionUID = -8030029795321975553L;
    private List<Process> testsRunning = new ArrayList<Process>();

    public List<Process> getTestsRunning() {
        //System.out.println("Size(testsRunning)="+testsRunning.size());
        return testsRunning;
    }

    public void setTestsRunning(List<Process> testsRunning) {
        this.testsRunning = testsRunning;
    }   
    
    public void addTestRunning(Process process) {
        this.testsRunning.add(process);
    }
    
    public void removeTestRunning(Process process){
        this.testsRunning.remove(process);
    }
    
}
