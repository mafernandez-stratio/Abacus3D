/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author miguel
 */
public class RunProcsHelper {
    
    private static Session session = null;

    public RunProcsHelper() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public LinkedHashSet<Date> getDates(){
        Transaction tx = session.beginTransaction();
        List listRunProcs = session.createQuery("FROM RunProcs").list();
        tx.commit();
        listRunProcs.iterator();
        LinkedHashSet<Date> dates = new LinkedHashSet<Date>();
        Iterator it = listRunProcs.iterator();
        while(it.hasNext()){
            RunProcs runProcs = (RunProcs) it.next();
            dates.add(runProcs.getDate());
        }
        return dates;
    }
    
    public ArrayList<RunProcs> getRunProcs(Date date){
        ArrayList arrayRunProcs = new ArrayList<RunProcs>();
        Transaction tx = session.beginTransaction();
        List listRunProcs = session.createQuery("SELECT * FROM RunProcs WHERE date = "+date).list();
        tx.commit();
        Iterator it = listRunProcs.iterator();
        while(it.hasNext()){
            arrayRunProcs.add(it.next());
        }
        return arrayRunProcs;
    }
    
    public ArrayList<RunProcs> getRunProcs(Date start, Date end){
        ArrayList arrayRunProcs = new ArrayList<RunProcs>();
        Transaction tx = session.beginTransaction();
        List listRunProcs = session.createQuery("SELECT * FROM RunProcs WHERE date>="+start+" AND date<="+end).list();
        tx.commit();
        Iterator it = listRunProcs.iterator();
        while(it.hasNext()){
            arrayRunProcs.add(it.next());
        }
        return arrayRunProcs;
    }
    
}
