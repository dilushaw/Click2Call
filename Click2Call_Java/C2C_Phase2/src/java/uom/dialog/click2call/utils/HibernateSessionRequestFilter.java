/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.utils;

import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.context.ThreadLocalSessionContext;

/**
 *
 * @author Dewmini
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
public class HibernateSessionRequestFilter implements Filter{

   //  private static Log log = LogFactory.getLog(HibernateSessionConversationFilter.class);
 
    private SessionFactory sf;
 
    public static final String HIBERNATE_SESSION_KEY = "hibernateSession";
    public static final String END_OF_CONVERSATION_FLAG = "endOfConversation";
 
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        
    //  if (((request.getParameter("type"))!=null && !request.getParameter("type").equalsIgnoreCase("ajax") ) || ((String)request.getParameter("type"))==null ){
         
        org.hibernate.classic.Session currentSession;
 
        // Try to get a Hibernate Session from the HttpSession
        HttpSession httpSession = ((HttpServletRequest) request).getSession();
        Session disconnectedSession = (Session) httpSession.getAttribute(HIBERNATE_SESSION_KEY);
 
        try {
 
            // Start a new conversation or in the middle?
            if (disconnectedSession == null) {
                currentSession = sf.openSession();
                currentSession.setFlushMode(FlushMode.COMMIT);
            } else {
                currentSession = (org.hibernate.classic.Session) disconnectedSession;
            }
 
           // ManagedSessionContext.bind(currentSession);
            ThreadLocalSessionContext.bind(currentSession);
 
            currentSession.beginTransaction();
 
            chain.doFilter(request, response);
 
           // currentSession = ManagedSessionContext.unbind(sf);
            currentSession = ThreadLocalSessionContext.unbind(sf);
 
            // End or continue the long-running conversation?
            if (request.getAttribute(END_OF_CONVERSATION_FLAG) != null
                    || request.getParameter(END_OF_CONVERSATION_FLAG) != null) {
 
                currentSession.flush();
 
                currentSession.getTransaction().commit();
 
                currentSession.close();
 
                httpSession.setAttribute(HIBERNATE_SESSION_KEY, null);
 
 
            } else {
               // currentSession.getTransaction().commit();
                httpSession.setAttribute(HIBERNATE_SESSION_KEY, currentSession);
            }
 
        } catch (StaleObjectStateException staleEx) {
         //   log.error("This interceptor does not implement optimistic concurrency control!");
        //    log.error("Your application will not work until you add compensation actions!");
            // Rollback, close everything, possibly compensate for any permanent changes
            // during the conversation, and finally restart business conversation. Maybe
            // give the user of the application a chance to merge some of his work with
            // fresh data... what you do here depends on your applications design.
            throw staleEx;
        } catch (Throwable ex) {
            // Rollback only
            try {
                if (sf.getCurrentSession().getTransaction().isActive()) {
                    sf.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
             System.out.println("Could not rollback transaction after exception! "+rbEx);
            } finally {
             //   log.error("Cleanup after exception!");
 
                // Cleanup
                System.out.println("Unbinding Session after exception");
            //    currentSession = ManagedSessionContext.unbind(sf);
                  currentSession = ThreadLocalSessionContext.unbind(sf);
               // System.out.println("Closing Session after exception");
                if(currentSession!=null)
                currentSession.close();
 
             //   System.out.println("Removing Session from HttpSession");
                httpSession.setAttribute(HIBERNATE_SESSION_KEY, null);
 
            }
 
            // Let others handle it... maybe another interceptor for exceptions?
            throw new ServletException(ex);
        }
        

    }
 
    public void init(FilterConfig filterConfig) throws ServletException {
      //  System.out.println("Initializing filter...");
      //  System.out.println("Obtaining SessionFactory from static HibernateUtil singleton");
        sf = HibernateUtil.getSessionFactory();
    }
 
    public void destroy() {
        //log.info("INSDIE DESTROY");
        // This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks wrto this class
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                //log.info(String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                e.printStackTrace();
                //log.info(String.format("Error deregistering driver %s", driver));
            }
    
        }

 

}
}