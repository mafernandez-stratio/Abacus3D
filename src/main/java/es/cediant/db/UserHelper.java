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
public class UserHelper {
    
    private static Session session = null;
    
    public UserHelper(){
        UserHelper.session = HibernateUtil.getSessionFactory().getCurrentSession();        
    }
    
    public User getUser(String username){
        List<User> list = null;
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("SELECT * FROM AbacusDB.User WHERE userName='admin';");
        list = (List<User>) q.list();
        return list.get(0);
    }
    
    public boolean usernameAlreadyExists(String username){
        if (getUser(username) == null){
            return false;
        } 
        return true;       
    }

    public void addUser(String username, String password) {
        //"INSERT INTO `AbacusDB`.`User` (`userName`, `password`, `Role`, `lastConnection`) VALUES ('cediant', 'cediant', 'User', '');"
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
    
}
