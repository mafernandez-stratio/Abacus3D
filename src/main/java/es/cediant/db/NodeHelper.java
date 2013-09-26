/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author miguel
 */
public class NodeHelper {
    
    private static SessionFactory factory = null;
    
    public NodeHelper(){
        factory = HibernateUtil.getSessionFactory();
    }
    
    public List getAllNodes(){
        Session session = factory.openSession();
        Transaction tx = null;
        List nodes = null;
        try {
            tx = session.beginTransaction();
            nodes = session.createQuery("FROM Node").list();            
            tx.commit();
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();             
            return nodes;
        }
    }        
    
    public Node getNodeByHostname(String hostname){
        Session session = factory.openSession();
        Transaction tx = null;
        Node node = null;
        try {
            tx = session.beginTransaction();
            node = (Node) session.createQuery("FROM Node a WHERE a.hostname like '"+hostname+"'").list().get(0);            
            tx.commit();
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();             
            return node;
        }
    }
    
    public Node getNodeByIP(String ip){
        Session session = factory.openSession();
        Transaction tx = null;
        Node node = null;
        try {
            tx = session.beginTransaction();
            node = (Node) session.createQuery("FROM Node a WHERE a.ip like '"+ip+"'").list().get(0);            
            tx.commit();
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();             
            return node;
        }
    }
    
    public Node getNode(int idNode){
        Session session = factory.openSession();
        Transaction tx = null;
        Node node = null;
        try {
            tx = session.beginTransaction();
            node = (Node) session.createQuery("FROM Node a WHERE a.idNode="+idNode).list().get(0);            
            tx.commit();
        } catch (HibernateException ex) {
            if (tx!=null){
                tx.rollback();
            }
            Logger.getLogger(ProcessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();             
            return node;
        }
    }
    
    public List getLastNodes(int num){
        List<Node> lastNodes = new ArrayList<Node>();
        List<Node> allNodes = new ArrayList<Node>();
        allNodes = getAllNodes();
        int maxSize = (allNodes.size()>num?num:allNodes.size());
        for(int i=0; i<maxSize; i++){
            lastNodes.add(allNodes.get(i));
        }
        return lastNodes;
    }
    
}
