package uaedunung.se.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import uaedunung.se.config.HibernateUtil;

import java.util.List;

public abstract class BaseDAO<T> {
    private final Class<T> type;

    protected BaseDAO(Class<T> type) {
        this.type = type;
    }

    public void save(T entity) {//Зберігає або оновлює об'єкт entity в базі даних
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public T findById(int id) {//Шукає і повертає об'єкт за його id з бд
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(type, id);
        }
    }

    public List<T> findAll() {//Повертає список всіх об'єктів типу T з бд
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from " + type.getSimpleName(), type).list();
        }
    }

    public void delete(T entity) {//Видаляє об'єкт з бази даних
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
