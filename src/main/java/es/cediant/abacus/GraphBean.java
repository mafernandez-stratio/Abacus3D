/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.structures.Focus;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author miguel
 */
@ManagedBean
@RequestScoped
public class GraphBean implements Serializable {

    private String sphereRadius;
    private String numbFocus;   
    private long startTime;
    private long endTime;
    private long executionTime;
    private boolean remote;    
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

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
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
    
    public void createImage() throws InterruptedException{
        this.setStartTime(System.currentTimeMillis());
        System.out.println("Creating image");
        Thread.sleep(4000);
        this.setEndTime(System.currentTimeMillis());
    }
    
}
