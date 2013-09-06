/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author miguel
 */
public class AppHelper {
    
    private static SessionFactory factory = null;
    
    public AppHelper(){
        factory = HibernateUtil.getSessionFactory();
    }
    
    public List getInstApps(){
        //System.out.println("");
        Session session = factory.openSession();
        Transaction tx = null;
        List apps = null;
        try {
            tx = session.beginTransaction();
            apps = session.createQuery("SELECT * FROM App a WHERE a.status like 'installed'").list();            
            tx.commit();
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();             
            return apps;
        }
    }
}
