package uom.dialog.click2call.dao;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author Anuradha
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
public class HibernateSession {

    Session session = null;

    public Session getSession() {

        if (session == null || !session.isOpen()) {
            HibernateFactory.buildSessionFactory();
            SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
            HibernateTemplate template = new HibernateTemplate(sessionFactory);

            session = template.getSessionFactory().getCurrentSession();
        }
        return session;
    }
}
