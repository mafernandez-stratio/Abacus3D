/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.structures.Focus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author miguel
 */
@ManagedBean
@SessionScoped
public class GraphBean implements Serializable {

    public static final int EXEC_LOCAL = 0;
    public static final int EXEC_REMOTE = 1;
    
    private String sphereRadius;
    private String numbFocus;   
    private long startTime;
    private long endTime;
    private long executionTime;
    private int typeExec;    
    private ArrayList<Focus> focuses;
    
    public GraphBean() {
        focuses = new ArrayList<Focus>();
    }
    
    public String getSphereRadius() {
        return sphereRadius;
    }

    public void setSphereRadius(String sphereRadius) {
        this.sphereRadius = sphereRadius;
    }

    public String getNumbFocus() {
        return numbFocus;
    }

    public void setNumbFocus(String numbFocus) {
        this.numbFocus = numbFocus;
    }
    
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }            

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
        this.setExecutionTime(this.endTime - this.startTime);
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public int getTypeExec() {
        return typeExec;
    }

    public void setTypeExec(int typeExec) {
        this.typeExec = typeExec;
    }

    public ArrayList getFocuses() {
        return focuses;
    }

    public void setFocus(ArrayList focuses) {
        this.focuses = focuses;
    }   
    
    public Focus getFocus(int pos){
        return focuses.get(pos);
    }
    
    public void addFocus(Focus focus){
        focuses.add(focus);
    }
    
    public String printFocuses(){
        StringBuilder strFocuses = new StringBuilder();
        for(int i=0; i<focuses.size(); i++){
            strFocuses.append("\n");
            strFocuses.append(focuses.get(i).printFocus());
            strFocuses.append("\n");
        }
        return strFocuses.toString();
    }
    
    public boolean isEmpty(){
        return focuses.isEmpty();
    }
    
    public boolean execTimeIsZero(){
        return Integer.toString((int) executionTime).equalsIgnoreCase("0");
    }
    
    public void createImage(ActionEvent event) {
        this.setStartTime(System.currentTimeMillis());
        System.out.println("Creating image");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        long randomAdd = (long) (Math.random()*100); 
        System.out.println("randomAdd="+randomAdd);
        System.out.println("Type Execution="+this.typeExec);
        this.setEndTime(System.currentTimeMillis()+randomAdd);        
    }       
    
    public void updateSphere(ActionEvent event){
        System.out.println("Updating sphere");
    }
}
