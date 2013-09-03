package com.nixsolutions.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.nixsolutions.dao.util.HibernateUtil;
import com.nixsolutions.domain.User;

/**
 * An implementation UserDao. This class uses a hibernate technology for work
 * with DB.
 * 
 * @author zinchenko
 * 
 */
public class HibernateUserDao implements UserDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private static UserDao userDao;

    /**
     * Get object of UserDao as singleton.
     * 
     * @return object of RoleDao
     */
    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new HibernateUserDao();
        }
        return userDao;
    }

    @Override
    public void create(User user) {

        if (user == null) {
            throw new NullPointerException();
        }

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.save(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    @Override
    public void update(User user) {

        if (user == null) {
            throw new NullPointerException();
        }

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.update(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void remove(User user) {

        if (user == null) {
            throw new NullPointerException();
        }

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.delete(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    @Override
    public List<User> findAll() {
        List<User> users = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();

            users = session.createCriteria(User.class).list();

            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
    }

    @Override
    public User findByLogin(String login) {

        if (login == null) {
            throw new NullPointerException();
        }

        User user = null;

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();

            Query query = session
                    .createQuery("FROM User user WHERE user.login = :login");
            query.setString("login", login);
            user = (User) query.uniqueResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {

        if (email == null) {
            throw new NullPointerException();
        }

        User user = null;

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();
            
            Query query = session
                    .createQuery("FROM User user WHERE user.email = :email");
            query.setString("email", email);
            user = (User) query.uniqueResult();
            
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public void remove(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();
            
            Query query = session
                    .createQuery("DELETE FROM User user WHERE user.id = :id");
            query.setLong("id", id);
            query.executeUpdate();
            
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public boolean isUserByLogin(String login) {
        if (login == null) {
            throw new NullPointerException();
        }

        boolean b = false;

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();
            
            Query query = session
                    .createQuery("FROM User user WHERE user.login = :login");
            query.setString("login", login);
            List<User> users = query.list();
            if (!users.isEmpty()) {
                return true;
            }
            
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    @Override
    public boolean isUserByEmail(String email) {
        if (email == null) {
            throw new NullPointerException();
        }

        boolean b = false;

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();
            
            Query query = session
                    .createQuery("FROM User user WHERE user.email = :email");
            query.setString("email", email);
            List<User> users = query.list();
            if (!users.isEmpty()) {
                return true;
            }
            
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return b;
    }

    @Override
    public User findById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }

        User user = null;

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();
            
            Query query = session
                    .createQuery("FROM User user WHERE user.id = :id");
            query.setLong("id", id);
            user = (User) query.uniqueResult();
            
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

}
