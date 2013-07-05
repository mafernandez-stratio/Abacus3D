/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author miguel
 */
public class UserHelper {
    
    private static Session session = null;
    
    public UserHelper(){
        //UserHelper.session = HibernateUtil.getSessionFactory().getCurrentSession(); 
        session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public User getUser(String username){
        List<User> list = null;
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("FROM User U WHERE U.userName='"+username+"'");
        list = (List<User>) q.list();
        session.getTransaction().commit();
        if(list.size()>0){
            return list.get(0);
        } else {
            return null;
        }
    }
    
    public boolean usernameAlreadyExists(String username){
        if (getUser(username) == null){
            return false;
        } 
        return true;       
    }

    public void addUser(String username, String password) {
        Transaction tx = null;
        tx = session.beginTransaction();
        User user = new User(username, password);
        session.save(user);
        tx.commit();
    }
    
    public boolean checkCredentials(String username, String password){
        User user = getUser(username);
        if(user == null){
            return false;
        } else if(user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public void removeUser(String username) {
        Transaction tx = null;
        tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("userName", username)).uniqueResult();
        session.delete(user);
        tx.commit();
    }
    
}
