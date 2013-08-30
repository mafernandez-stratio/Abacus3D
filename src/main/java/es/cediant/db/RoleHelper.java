/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

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
public class RoleHelper {
    
    private static SessionFactory factory = null;
    
    public RoleHelper(){
        //UserHelper.session = HibernateUtil.getSessionFactory().getCurrentSession(); 
        factory = HibernateUtil.getSessionFactory();
    }

    public Role getRole(String rolename) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Role> list = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM Role R WHERE R.roleName='"+rolename+"'");
            list = (List<Role>) q.list();
            tx.commit();            
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close(); 
            if((list == null) || (list.size()<=0)){
                return null;
            } else {
                return list.get(0);
            } 
            
        }        
    }

    public Role getRole(int roleInt) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Role> list = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM Role R WHERE R.idRole='"+roleInt+"'");
            list = (List<Role>) q.list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close(); 
            if((list == null) || (list.size()<=0)){
                return null;
            } else {
                return list.get(0);
            } 
            
        }   
    }
    
    public List<Role> getRoles() {
        Session session = factory.openSession();
        Transaction tx = null;
        List roles = null;
        try {
            tx = session.beginTransaction();
            roles = session.createQuery("FROM Role").list();
            tx.commit();        
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();
            return roles;            
        }
    }
    
}
