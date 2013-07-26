/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author miguel
 */
public class RoleHelper {
    
    private static Session session = null;
    
    public RoleHelper(){
        //UserHelper.session = HibernateUtil.getSessionFactory().getCurrentSession(); 
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public Role getRole(String rolename) {
        List<Role> list = null;
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("FROM Role R WHERE R.roleName='"+rolename+"'");
        list = (List<Role>) q.list();
        session.getTransaction().commit();
        if(list.size()>0){
            return list.get(0);
        } else {
            return null;
        }        
    }

    public Role getRole(int roleInt) {
        List<Role> list = null;
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("FROM Role R WHERE R.idRole='"+roleInt+"'");
        list = (List<Role>) q.list();
        session.getTransaction().commit();
        if(list.size()>0){
            return list.get(0);
        } else {
            return null;
        }
    }
    
    public List<Role> getRoles() {
        Transaction tx = session.beginTransaction();
        List roles = session.createQuery("FROM Role").list();
        tx.commit();
        return roles;
    }
    
}
