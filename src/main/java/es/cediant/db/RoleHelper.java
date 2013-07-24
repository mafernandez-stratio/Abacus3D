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
class RoleHelper {
    
    private static Session session = null;
    
    public RoleHelper(){
        //UserHelper.session = HibernateUtil.getSessionFactory().getCurrentSession(); 
        session = HibernateUtil.getSessionFactory().openSession();
    }

    Role getRole(String rolename) {
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
    
}
