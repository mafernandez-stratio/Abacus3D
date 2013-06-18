/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author miguel
 */
@ManagedBean
@ViewScoped
public class nBodysBean implements Serializable {
    private static final long serialVersionUID = 1L;
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
    Thread thread;
    
    /**
     * Creates a new instance of nBodysBean
     */
    public nBodysBean() {
        //System.out.println("new NBody Bean");
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
    
    public void launchCalculation(){
        //System.out.println("launchCalculation");
        //try {
            if(getDevice().equalsIgnoreCase("host")){
                setShowHost(true);
            } else if (getDevice().equalsIgnoreCase("xeon")){
                setShowXeon(true);
            } else {
                setShowHost(true);
                setShowXeon(true);
            }
            /*System.out.println("nParticles="+nParticles);
            System.out.println("Steps="+nSteps);
            System.out.println("dt="+dt);
            System.out.println("device="+device);*/
            ArrayList<String> params = new ArrayList<String>();
            params.add("./abacus");
            params.add("Abacus");
            params.add("Integer.toString(nParticles)");
            params.add("Integer.toString(nSteps)");
            params.add("Float.toString(dt)");
            ExternalCommand ec = new ExternalCommand("/workspace/OpenGLGLU/src", params);
            thread = new Thread(ec, "abacus");
            thread.start();
            /*ProcessBuilder pb = new ProcessBuilder(
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
            p.destroy();*/
        //} catch (IOException ex) {
        //    Logger.getLogger(nBodysBean.class.getName()).log(Level.SEVERE, null, ex);
        //} catch (InterruptedException ex) {
        //    Logger.getLogger(nBodysBean.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }                
    
    public boolean threadIsRunning(){
        try {
            if (thread.isAlive()){
                //System.out.println("Process running...");
                return true;
            } else {
                //System.out.println("Process done");
                return false;
            }
        } catch (Throwable ex) {
            Logger.getLogger(nBodysBean.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } 
    }
                        
    private boolean buttonRendered = true;
    private boolean enabled = false;
    private int currentValue;
 
    public String startProcess() {
        setEnabled(true);
        setButtonRendered(false);
        setCurrentValue(0);
        launchCalculation();
        return null;
    }
 
    public void increment() {
        /*System.out.println("Current Value = "+currentValue);
        System.out.println(nParticles);
        System.out.println(nSteps);
        System.out.println(dt);
        System.out.println(device);*/
        if (isEnabled() && (currentValue < 100)) {
            currentValue += 5;
            if(currentValue >= 100){
                currentValue = 5;
            } /*else {
                System.out.println("New Current Value = "+currentValue);
            }*/
            boolean threadDone = !threadIsRunning();            
            if(threadDone){
                //System.out.println("Calculation Done");
                currentValue = 100;
                setShowCalc(true);
                setButtonRendered(true);
            } /*else {
                System.out.println("Calculating...");
            }*/
        }
    }
 
    public boolean isEnabled() {
        return enabled;
    }
 
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
 
    public boolean isButtonRendered() {
        return buttonRendered;
    }
 
    public void setButtonRendered(boolean buttonRendered) {
        this.buttonRendered = buttonRendered;
    }
 
    public int getCurrentValue() {
        if (!isEnabled()) {
            return -1;
        }
        return currentValue;
    }
 
    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
 

    
    
    
    
    
}   
