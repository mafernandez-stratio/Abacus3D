/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.util;

import es.cediant.abacus.TestsBean;
import es.cediant.db.ProcessHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */
public class ExecThread extends Thread {

    private String command;
    private es.cediant.db.Process process;
    private TestsBean testsBean;

    public ExecThread(String command, es.cediant.db.Process process, TestsBean testsBean) {
        this.command = command;
        this.process = process;
        this.testsBean = testsBean;
    }   

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public es.cediant.db.Process getProcess() {
        return process;
    }

    public void setProcess(es.cediant.db.Process process) {
        this.process = process;
    }       

    public TestsBean getTestsBean() {
        return testsBean;
    }

    public void setTestsBean(TestsBean testsBean) {
        this.testsBean = testsBean;
    }        
    
    @Override
    public void run(){
        ProcessHelper ph = new ProcessHelper();
        try {
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(" >> "+line);
                line = reader.readLine();                
            }      
                        
            ph.modify(process, "finished");            
            
            testsBean.removeTestRunning(process);
            
            /*
            TopicsContextMessageProducer tcmp = new TopicsContextMessageProducer();
            tcmp.sendMessage();
            
            System.out.println("Message sent");
            */
            
            /*
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, 
                    "Test with id="+idProcess+" finished", 
                    "Test with id="+idProcess+" finished")
            );
            */ 
                  
        } catch (InterruptedException ex) {
            Logger.getLogger(ExecThread.class.getName()).log(Level.SEVERE, null, ex);
            ph.modify(process, "error");
        } catch (IOException ex) {
            Logger.getLogger(ExecThread.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("IOException ex");
            ph.modify(process, "error");
        }
    }
}
