package uaedunung.se.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() { //Створює SessionFactory за допомогою конфігурації з файлу hibernate.cfg.xml, що містить налаштування для Hibernate.
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {//Повертає створений SessionFactory для подальшого використання в додатку.
        return sessionFactory;
    }

    public static void shutdown() {//Закриває SessionFactory
        getSessionFactory().close();
    }
}
