/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author miguel
 */
public class RunProcsHelper {
    
    private static SessionFactory factory = null;

    public RunProcsHelper() {
        factory = HibernateUtil.getSessionFactory();
    }
    
    public LinkedHashSet<Date> getDates(){
        Session session = factory.openSession();
        Transaction tx = null;
        LinkedHashSet<Date> dates = null;
        try {        
            tx = session.beginTransaction();
            List listRunProcs = session.createQuery("FROM RunProcs").list();
            tx.commit();
            listRunProcs.iterator();
            dates = new LinkedHashSet<Date>();
            Iterator it = listRunProcs.iterator();
            while(it.hasNext()){
                RunProcs runProcs = (RunProcs) it.next();
                dates.add(runProcs.getDate());
            }                         
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close(); 
            return dates;
        }            
    }
    
    public ArrayList<RunProcs> getRunProcs(Date date){
        Session session = factory.openSession();
        Transaction tx = null;        
        ArrayList arrayRunProcs = new ArrayList<RunProcs>();
        try {
            tx = session.beginTransaction();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List listRunProcs = session.createQuery("FROM RunProcs WHERE date = "+dateFormat.format(date)).list();
            tx.commit();
            Iterator it = listRunProcs.iterator();
            while(it.hasNext()){
                arrayRunProcs.add(it.next());
            }            
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close(); 
            return arrayRunProcs;
        }                        
    }
    
    public ArrayList<RunProcs> getRunProcs(Date start, Date end){
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList arrayRunProcs = new ArrayList<RunProcs>();        
        try {                    
            tx = session.beginTransaction();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strQuery = "FROM RunProcs R WHERE R.date >= '"+dateFormat.format(start)+"' AND R.date < '"+dateFormat.format(end)+"'";
            //System.out.println(strQuery);
            Query query = session.createQuery(strQuery);
            List listRunProcs = query.list();
            tx.commit();
            Iterator it = listRunProcs.iterator();
            while(it.hasNext()){
                arrayRunProcs.add(it.next());
            }                
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close(); 
            return arrayRunProcs;
        }         
    }
    
}
