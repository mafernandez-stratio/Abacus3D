/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import org.hibernate.Query;
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
        List listRunProcs = session.createQuery("FROM RunProcs WHERE date = "+date).list();
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
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        /*
        Calendar cal = Calendar.getInstance();
        StringBuilder startDate = new StringBuilder();
        cal.setTime(start);
        startDate.append(cal.get(Calendar.YEAR)+"-");
        startDate.append(cal.get(Calendar.MONTH)+"-");
        startDate.append(cal.get(Calendar.DAY_OF_MONTH)+" ");
        startDate.append(cal.get(Calendar.HOUR_OF_DAY)+":");
        startDate.append(cal.get(Calendar.MINUTE)+":");
        startDate.append(cal.get(Calendar.SECOND));
        
        StringBuilder endDate = new StringBuilder();
        cal.setTime(end);
        endDate.append(cal.get(Calendar.YEAR)+"-");
        endDate.append(cal.get(Calendar.MONTH)+"-");
        endDate.append(cal.get(Calendar.DAY_OF_MONTH)+" ");
        endDate.append(cal.get(Calendar.HOUR_OF_DAY)+":");
        endDate.append(cal.get(Calendar.MINUTE)+":");
        endDate.append(cal.get(Calendar.SECOND));
        */
         
        String strQuery = "FROM RunProcs R WHERE R.date >= '"+dateFormat.format(start)+"' AND R.date <= '"+dateFormat.format(end)+"'";
        
        System.out.println(strQuery);
        
        Query query = session.createQuery(strQuery);
        List listRunProcs = query.list();
        tx.commit();
        Iterator it = listRunProcs.iterator();
        while(it.hasNext()){
            arrayRunProcs.add(it.next());
        }
        return arrayRunProcs;
    }
    
}
