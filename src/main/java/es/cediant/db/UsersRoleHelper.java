/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import java.util.ArrayList;
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
public class UsersRoleHelper {
 
    private static SessionFactory factory = null;
    
    public UsersRoleHelper(){
        //UserHelper.session = HibernateUtil.getSessionFactory().getCurrentSession(); 
        factory = HibernateUtil.getSessionFactory();
    }

    public ArrayList<UsersRole> getUsersRole(){
        Session session = factory.openSession();
        Transaction tx = null;        
        ArrayList<UsersRole> roles = new ArrayList<UsersRole>();
        try {                        
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM UsersRole");
            System.out.println(q.list().toString());
            roles = (ArrayList<UsersRole>) q.list();
            tx.commit();
            if(roles.size()>0){
                return roles;
            } else {
                return null;
            }        
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
    
    public boolean addEntry(User user, Role role) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean result = false;
        try {        
            tx = session.beginTransaction();
            UsersRole usersRole = new UsersRole(user, role);
            session.save(usersRole);
            tx.commit();     
            result = true;
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close(); 
            return result;
        }
    }

    public ArrayList<UsersRole> getRoles(int userId) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList<UsersRole> roles = new ArrayList<UsersRole>();
        try {                    
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM UsersRole U WHERE U.user.idUser = "+userId);
            System.out.println(q.list().toString());
            roles = (ArrayList<UsersRole>) q.list();
            tx.commit();
            if(roles.size()>0){
                return roles;
            } else {
                return null;
            }                
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

    public boolean removeAllRolesOfUser(Integer idUser) {
        //DELETE FROM `AbacusDB`.`UsersRole` WHERE `idUsersRole`='9';
        /*ArrayList<UsersRole> usersRole = new ArrayList<UsersRole>();
        usersRole = getRoles(idUser);
        for(UsersRole userRole: usersRole){
            
        }*/
        Session session = factory.openSession();
        Transaction tx = null;
        boolean result = false;
        try {        
            tx = session.beginTransaction();
            Query q = session.createQuery("DELETE FROM UsersRole U WHERE U.user.idUser = "+idUser);
            int rowsAffected = q.executeUpdate();
            tx.commit();
            //System.out.println("Rows affected = "+rowsAffected); 
            if(rowsAffected > 0){
                result = true;
            }
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close(); 
            return result;
        }
    }

    public boolean addEntry(Integer idUser, Integer idRole) throws Exception {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean result = false;
        try {        
            //INSERT INTO `AbacusDB`.`UsersRole` (`idUser`, `idRole`) VALUES ('4', '3');
            tx = session.beginTransaction();
            Query q = session.createQuery("INSERT INTO UsersRole ("+idUser+", "+idRole+")");
            int rowsAffected = q.executeUpdate();
            tx.commit();
            //System.out.println("Rows affected = "+rowsAffected);
            //throw new Exception("Not implemented yet");
            if(rowsAffected > 0){
                result = true;
            }
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close(); 
            return result;
        } 
    }

}
