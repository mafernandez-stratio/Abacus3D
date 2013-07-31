/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import java.util.ArrayList;
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

    public ArrayList<UsersRole> getUsersRole(){
        ArrayList<UsersRole> roles = new ArrayList<UsersRole>();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("FROM UsersRole");
        System.out.println(q.list().toString());
        roles = (ArrayList<UsersRole>) q.list();
        tx.commit();
        if(roles.size()>0){
            return roles;
        } else {
            return null;
        }
    }
    
    public void addEntry(User user, Role role) {
        Transaction tx = session.beginTransaction();
        UsersRole usersRole = new UsersRole(user, role);
        session.save(usersRole);
        tx.commit();
    }

    public ArrayList<UsersRole> getRoles(int userId) {
        ArrayList<UsersRole> roles = new ArrayList<UsersRole>();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("FROM UsersRole U WHERE U.user.idUser = "+userId);
        System.out.println(q.list().toString());
        roles = (ArrayList<UsersRole>) q.list();
        tx.commit();
        if(roles.size()>0){
            return roles;
        } else {
            return null;
        }        
    }

    public void removeAllRolesOfUser(Integer idUser) {
        //DELETE FROM `AbacusDB`.`UsersRole` WHERE `idUsersRole`='9';
        /*ArrayList<UsersRole> usersRole = new ArrayList<UsersRole>();
        usersRole = getRoles(idUser);
        for(UsersRole userRole: usersRole){
            
        }*/
        
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("DELETE FROM UsersRole U WHERE U.user.idUser = "+idUser);
        int rowsAffected = q.executeUpdate();
        tx.commit();
        System.out.println("Rows affected = "+rowsAffected);
    }

    public void addEntry(Integer idUser, Integer idRole) throws Exception {
        //INSERT INTO `AbacusDB`.`UsersRole` (`idUser`, `idRole`) VALUES ('4', '3');
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("INSERT INTO UsersRole ("+idUser+", "+idRole+")");
        int rowsAffected = q.executeUpdate();
        tx.commit();
        System.out.println("Rows affected = "+rowsAffected);
        throw new Exception("Not implemented yet");
    }

}
