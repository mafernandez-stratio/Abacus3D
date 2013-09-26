/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.db.ProcessHelper;
import es.cediant.db.Process;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author miguel
 */
public class ProcessesBean implements Serializable {
    
    private static final long serialVersionUID = 5651395627768122272L;
    
    private List<Process> allProcesses;
    private String action;
    private HashMap<Integer, String> mapForms = new HashMap<Integer, String>();
    private HashMap<Integer, Boolean> nullActions = new HashMap<Integer, Boolean>();

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

    public void valueChanged(ValueChangeEvent event) {
        System.out.println(" <<< valueChanged >>> ");
        String newValue = (String) event.getNewValue(); 
        System.out.println("newValue="+newValue);
        UIComponent uiComponent = event.getComponent();        
        String clientId = uiComponent.getClientId();
        System.out.println("clientId="+clientId);
        int index = clientId.lastIndexOf("processesTable");
        System.out.println("index="+index);
        char tmpChar = clientId.charAt(index+15);
        System.out.println("tmpChar="+tmpChar);
        int row = Character.digit(tmpChar, 10);
        System.out.println("row="+row);
        mapForms.put(row, newValue);
        System.out.println("mapForms.size="+mapForms.size());
        nullActions.put(row, Boolean.FALSE);
        System.out.println(" >>> valueChanged <<< ");
    }
    
    public void select(String itIndex){
        System.out.println("unselected="+itIndex);
        System.out.println("currentAction="+(action==null?"null":action));
        boolean isNull = nullActions.get(Integer.parseInt(itIndex));
        if(isNull){
            mapForms.put(Integer.parseInt(itIndex), action);
            System.out.println("Nulling...");
        }
        nullActions.put(Integer.parseInt(itIndex), Boolean.TRUE);
    }
    
    public void executeActions(){        
        System.out.println("Executing actions...");
        ProcessHelper ph = new ProcessHelper();
        String newAction = null;
        for(int row: mapForms.keySet()){        
            newAction = mapForms.get(row);
            System.out.println(" - "+row+": "+mapForms.get(row));
            if((newAction!=null) && (!newAction.equalsIgnoreCase("null"))){
                ph.modify(allProcesses.get(row).getIdProcess(), newAction);
            }
        }
        action = null;
        mapForms.clear();
        nullActions.clear();
    }
    
    public List<Process> getLastProcesses(int num){
        ProcessHelper ph = new ProcessHelper();
        return ph.getLastProcesses(num);        
    }
        
}

