/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    private static final long serialVersionUID = 6101150443955943422L;   
    @Min(value = 2)
    @Max(value = 50000)
    Integer numParticles = 100;
    @Min(value = 2)
    @Max(value = 999)
    Integer numSteps = 100;
    @Min(value = (long) 0.01f)
    @Max(value = (long) 2f)
    Float dt = 0.1f;   
    boolean showCalc = false;  
    boolean showHost = false;
    boolean showXeon = false;
    String device = "both";  
    Thread thread;
    boolean buttonRendered = true;
    boolean enabled = false;
    int currentValue;
    String coordinates;    
    
    /**
     * Creates a new instance of nBodysBean
     */
    public nBodysBean() {
        //System.out.println("new NBody Bean");
        //coordinates="4409;0.9831,-0.4179,0.0416_4440;-0.4146,-0.3789,0.4994*4429;0.0855,0.0635,1.5552_4406;-0.5918,0.6306,0.7046";
    }

    public Integer getNumParticles() {
        return numParticles;
    }

    public void setNumParticles(Integer numParticles) {
        this.numParticles = numParticles;
    }

    public Integer getNumSteps() {
        return numSteps;
    }

    public void setNumSteps(Integer numSteps) {
        this.numSteps = numSteps;
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
        //System.out.println(" - launchCalculation");
        //try {            
            /*System.out.println("nParticles="+nParticles);
            System.out.println("Steps="+nSteps);
            System.out.println("dt="+dt);
            System.out.println("device="+device);
            System.out.println(showXeon);
            System.out.println(showHost);*/
            ArrayList<String> params = new ArrayList<String>();
            params.add("./abacus");
            params.add("Abacus");
            params.add(Integer.toString(numParticles));
            params.add(Integer.toString(numSteps));
            params.add(Float.toString(dt));
            params.add(device);
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
 
    public String startProcess() {
        /*System.out.println(" - startProcess");
        System.out.println("nParticles="+numParticles);
        System.out.println("Steps="+numSteps);
        System.out.println("dt="+dt);
        System.out.println("device="+device);
        System.out.println(showXeon);
        System.out.println(showHost);*/
        setEnabled(true);
        setButtonRendered(false);
        setShowCalc(false);    
        setShowXeon(false);
        setShowHost(false);
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
                currentValue = 100;
                setShowCalc(true);                
                if(getDevice().equalsIgnoreCase("host")){
                    setShowHost(true);
                } else if (getDevice().equalsIgnoreCase("xeon")){
                    setShowXeon(true);
                } else if (getDevice().equalsIgnoreCase("both")){
                    setShowHost(true);
                    setShowXeon(true);
                }
                setButtonRendered(true);
                setEnabled(false);
                /*System.out.println(" - Calculation Done");
                System.out.println("nParticles="+numParticles);
                System.out.println("Steps="+numSteps);
                System.out.println("dt="+dt);
                System.out.println("device="+device); 
                System.out.println(showXeon);
                System.out.println(showHost);*/
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
    
    public String readCoordinates(){
        // Read all frameXXX.txt and fill the variable coordinates where:
        // ; represents the final of the number of the particle
        // , represents the final of a coordinate
        // _ represents the final of the particle's coordinates
        // * represents the final of a frame   
        //System.out.println("Num frames="+numSteps);
        try {      
            StringBuilder sb = new StringBuilder();
            for(int i=1; i<=numSteps; i++){
                String number = Integer.toString(i);
                if(i<10){
                    number = "00"+(i);
                } else if (i<100){                    
                    number = "0"+(i);
                }
                File file = new File("workspace/AbacusHTML5/src/main/webapp/resources/txt/frame"+number+".txt");
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                int nLine = 0;
                while ((line = br.readLine()) != null) {
                    // process the line.
                    sb.append(line);
                    if((nLine+1)<numParticles){
                        sb.append("_");
                    }   
                    nLine++;
                }
                if(i<numSteps){
                    sb.append("*");
                }
                br.close();
            }
            coordinates = sb.toString();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(nBodysBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(nBodysBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}   
