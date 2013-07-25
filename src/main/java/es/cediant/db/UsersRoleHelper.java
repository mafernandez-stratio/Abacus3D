/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author miguel
 */
class UsersRoleHelper {
 
    private static Session session = null;
    
    public UsersRoleHelper(){
        //UserHelper.session = HibernateUtil.getSessionFactory().getCurrentSession(); 
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public void addEntry(User user, Role role) {
        Transaction tx = session.beginTransaction();
        UsersRole usersRole = new UsersRole(user, role);
        session.save(usersRole);
        tx.commit();
    }

}
