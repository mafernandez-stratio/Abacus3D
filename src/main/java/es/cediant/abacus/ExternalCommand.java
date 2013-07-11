/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */
public class ExternalCommand implements Runnable {
    
    String directory;
    ArrayList<String> parameters;
    
    public ExternalCommand (String directory, ArrayList<String> parameters){
        this.directory = directory;
        this.parameters = parameters;
    }

    @Override
    public void run(){
        try {
            /*ProcessBuilder pb = new ProcessBuilder(
                "./abacus", 
                "Abacus", 
                Integer.toString(nParticles), 
                Integer.toString(nSteps), 
                Float.toString(dt));*/
            ProcessBuilder pb = new ProcessBuilder(parameters);
            String userHome = System.getProperty("user.home");
            //File directory = new File(userHome+"/workspace/OpenGLGLU/src");
            File folder = new File(userHome+directory);
            pb.directory(folder);
            Process p = pb.start();
            p.waitFor();
            p.destroy();
        } catch (IOException ex) {
            Logger.getLogger(ExternalCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ExternalCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
