package company.Hibernate;

import company.Constants;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println(Constants.INITIAL_SESSION_FACTORY_CREATION_FAILED + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
