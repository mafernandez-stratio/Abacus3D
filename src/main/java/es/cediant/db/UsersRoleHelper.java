/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author miguel
 */
public class UsersRoleHelper {
 
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

    public ArrayList<Role> getRoles(int userId) {
        ArrayList<Role> roles = new ArrayList<Role>();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("FROM UsersRole U WHERE U.user.idUser = "+userId);
        System.out.println(q.list().toString());
        roles = (ArrayList<Role>) q.list();
        tx.commit();
        if(roles.size()>0){
            return roles;
        } else {
            return null;
        }        
    }

}
