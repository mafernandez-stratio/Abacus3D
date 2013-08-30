/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author miguel
 */
public class UserHelper {
    
    private static SessionFactory factory = null;
    
    public UserHelper(){
        //UserHelper.session = HibernateUtil.getSessionFactory().getCurrentSession(); 
        factory = HibernateUtil.getSessionFactory();
    }
    
    public User getUser(String username){
        Session session = factory.openSession();
        Transaction tx = null;
        User user = null;        
        try {
            List<User> list = null;
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM User U WHERE U.userName='"+username+"'");
            list = (List<User>) q.list();
            session.getTransaction().commit();
            if((list != null) && (list.size()>0)){            
                user = list.get(0);
            }
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close(); 
            return user;
        }                              
    }
    
    public boolean usernameAlreadyExists(String username){
        if (getUser(username) == null){
            return false;
        } 
        return true;       
    }

    public boolean addUser(String username, String password) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean result = true;
        try {
            tx = session.beginTransaction();
            User user = new User(username, password);
            session.save(user);
            tx.commit();            
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            session.close(); 
            return result;
        } 
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

    public boolean removeUser(String username) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean result = true;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            User user = (User) criteria.add(Restrictions.eq("userName", username)).uniqueResult();
            session.delete(user);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            session.close(); 
            return result;
        } 
    }

    public boolean updateLastConnection(String username, Date date) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean result = true;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("UPDATE User SET lastConnection = :lastConn WHERE userName = :user");
            query.setParameter("lastConn", date);
            query.setParameter("user", username);
            //UPDATE `AbacusDB`.`User` SET `lastConnection`='2013-07-01 12:10:23' WHERE `userName`='admin';
            query.executeUpdate();
            tx.commit();        
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            session.close(); 
            return result;
        }
    }

    public ArrayList<String> getRoles(String username) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList<String> roles = new ArrayList<String>();
        try {
            User user = getUser(username);
            Set setRoles = user.getUsersRoles();
            for(Iterator iter = setRoles.iterator(); iter.hasNext();){
                UsersRole userRole = (UsersRole) iter.next();
                Role role = userRole.getRole();            
                roles.add(role.getRoleName());
            }        
            return roles;
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
    
    public boolean addRole(String username, String rolename){
        Session session = factory.openSession();
        Transaction tx = null;
        boolean result = true;
        try {
            User user = getUser(username);
            RoleHelper roleHelper = new RoleHelper();
            Role role = roleHelper.getRole(rolename);
            UsersRoleHelper usersRoleHelper = new UsersRoleHelper();        
            usersRoleHelper.addEntry(user, role);
            /*ArrayList<String> roles = new ArrayList<String>();
            roles = getRoles(username);
            if(!roles.contains(role)){            
                Transaction tx = null;
                tx = session.beginTransaction();
                Query query = session.createQuery("UPDATE User SET Role = :role WHERE userName = :user");
                query.setParameter("role", roles.toString().substring(1, roles.toString().length()-1)+", "+role);
                query.setParameter("user", username);        
                query.executeUpdate();
                //System.out.println(roles.toString().substring(1, roles.toString().length()-1)+", "+role);
                tx.commit();
            }*/        
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            session.close(); 
            return result;
        }        
    }

    public List<User> getUsers() {
        Session session = factory.openSession();
        Transaction tx = null;
        List users = null;
        try {
            tx = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            tx.commit();
            return users;
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close(); 
            return users;
        } 
    }

    public boolean updateUser(int i, User editedUser) {    
        Session session = factory.openSession();
        Transaction tx = null;
        boolean result = true;
        try {
            // UPDATE `AbacusDB`.`User` SET `created`='2013-07-01 10:00:01', `lastConnection`='2013-07-30 08:52:44' WHERE `idUser`='3';
            //System.out.println(" === UpdateUser ==== ");
            //Transaction tx = session.beginTransaction();    
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, i);
            if(editedUser.getComment() !=  null){                   
                user.setCreated(editedUser.getCreated());
            }
            if(editedUser.getCreated() !=  null){                   
                user.setComment(editedUser.getComment());
            }
            if(editedUser.getIdUser() !=  null){
                user.setIdUser(editedUser.getIdUser());
            }
            if(editedUser.getLastConnection() !=  null){
                user.setLastConnection(editedUser.getLastConnection());
            }
            if(editedUser.getPassword() !=  null){
                user.setPassword(editedUser.getPassword());
            }
            if(editedUser.getProcesses() !=  null){
                user.setProcesses(editedUser.getProcesses());
            }
            if(editedUser.getUserName() !=  null){
                user.setUserName(editedUser.getUserName());
            }
            if(editedUser.getUsersRoles() !=  null){
                user.setUsersRoles(editedUser.getUsersRoles());
            }
            tx.commit();
            /*            
            user.setUsersRoles(editedUser.getUsersRoles());  
            Set tmpSet = editedUser.getUsersRoles();            
            Iterator iter = tmpSet.iterator();            
            while(iter.hasNext()){
                UsersRole usersRole = (UsersRole) iter.next();
                System.out.println(usersRole.getRole().getRoleName());
            }
            */
            //tx.commit();
            //System.out.println(" === UpdateUser ==== ");
        }  catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            session.close(); 
            return result;
        } 
    }

    public boolean updateRoles(int i, List<Role> selectedRoles) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean result = true;        
        try {
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, i);
            Set set = new HashSet(selectedRoles);        
            user.setUsersRoles(set);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            session.close(); 
            return result;
        } 
    }

    public boolean setCreationDate(String username, Date date) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean result = true;
        try {
            tx = session.beginTransaction();
            //UPDATE `AbacusDB`.`User` SET `lastConnection`='2013-07-01 12:10:23' WHERE `userName`='admin'; 
            /*
            Query query = session.createQuery("UPDATE User SET created = :created WHERE userName = :user");
            query.setParameter("created", date);
            query.setParameter("user", username);
            query.executeUpdate();
            */
            User user = getUser(username);
            user = (User) session.get(User.class, user.getIdUser());   
            user.setCreated(date);
            tx.commit();        
        }  catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            session.close(); 
            return result;
        } 
    }
    
}
