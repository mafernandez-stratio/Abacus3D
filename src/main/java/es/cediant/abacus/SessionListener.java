/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author miguel
 */
public class SessionListener implements HttpSessionListener {   
    
    public SessionListener() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
       
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession httpSession = se.getSession();
        ServletContext servletContext = httpSession.getServletContext();
        RequestDispatcher tmp = servletContext.getRequestDispatcher("/login.xhtml");
        System.out.println("Session destroyed");
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/login.xhtml");        
    }
}
