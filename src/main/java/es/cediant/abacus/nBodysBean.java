/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author miguel
 */
@ManagedBean
@RequestScoped
public class nBodysBean implements Serializable {

    @Min(value = 2)
    @Max(value = 999)
    private Integer nParticles;
    @Min(value = 9)
    @Max(value = 999)
    private Integer nSteps;
    @Min(value = (long) 0.01f)
    @Max(value = (long) 1f)
    private Float dt;   
    boolean showCalc = false;  
    boolean showHost = false;
    boolean showXeon = false;
    String device;
    
    /**
     * Creates a new instance of nBodysBean
     */
    public nBodysBean() {
        device="both";
    }
    
    public Integer getnParticles() {
        return nParticles;
    }

    public void setnParticles(Integer nParticles) {
        this.nParticles = nParticles;
    }

    public Integer getnSteps() {
        return nSteps;
    }

    public void setnSteps(Integer nSteps) {
        this.nSteps = nSteps;
    }

    public Float getDt() {
        return dt;
    }

    public void setDt(Float dt) {
        this.dt = dt;
    }
    
    public boolean isShowHost() {
        return showHost;
    }

    public void setShowHost(boolean showHost) {
        this.showHost = showHost;
    }

    public boolean isShowXeon() {
        return showXeon;
    }

    public void setShowXeon(boolean showXeon) {
        this.showXeon = showXeon;
    }
    
    public boolean isShowCalc() {
        return showCalc;
    }

    public void setShowCalc(boolean showCalc) {
        this.showCalc = showCalc;
    }            
    
    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
    
    public String launchCalculation(ActionEvent event){
        String startProcess = "";
        try {
            this.setShowCalc(true);
            if(this.getDevice().equalsIgnoreCase("host")){
                this.setShowHost(true);
            } else if (this.getDevice().equalsIgnoreCase("xeon")){
                this.setShowXeon(true);
            } else {
                this.setShowHost(true);
                this.setShowXeon(true);
            }
            /*
            System.out.println("nParticles="+nParticles);
            System.out.println("Steps="+nSteps);
            System.out.println("dt="+dt);
            System.out.println("device="+device);
            */
            ProcessBuilder pb = new ProcessBuilder(
                    "./abacus", 
                    "Abacus", 
                    Integer.toString(nParticles), 
                    Integer.toString(nSteps), 
                    Float.toString(dt));
            String userHome = System.getProperty("user.home");
            File directory = new File(userHome+"/workspace/OpenGLGLU/src");
            pb.directory(directory);
            Process p = pb.start();
            p.waitFor();
            p.destroy();
        } catch (IOException ex) {
            Logger.getLogger(nBodysBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(nBodysBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("Calculation Done");
        return startProcess;
    }            
    
    public void launchJS(ActionEvent event){
    
    }   
 
}   
