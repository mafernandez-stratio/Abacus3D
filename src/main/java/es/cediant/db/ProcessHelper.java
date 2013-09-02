/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */
public class ProcessHelper {
    
    //private static Session session = null;
    private static SessionFactory factory = null;
    
    public ProcessHelper(){
        //session = HibernateUtil.getSessionFactory().openSession();
        factory = HibernateUtil.getSessionFactory();
    }
    
    /* Method to  READ all the employees */
    public List listProcesses(){
        System.out.println("Listing all processes...");
        Session session = factory.openSession();
        Transaction tx = null;
        List processes = null;
        try {
            tx = session.beginTransaction();
            processes = session.createQuery("FROM Process").list();
            /*for (Iterator iterator = processes.iterator(); iterator.hasNext();){
                Process process = (Process) iterator.next();  
            }*/
            tx.commit();
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close(); 
            if(processes!=null){
                System.out.println(processes.size()+" processes found");
            }
            return processes;
        }
    }
    
}
