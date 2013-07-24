/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import org.hibernate.Session;

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
}
