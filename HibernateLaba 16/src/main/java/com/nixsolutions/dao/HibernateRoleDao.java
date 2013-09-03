package com.nixsolutions.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.nixsolutions.dao.util.HibernateUtil;
import com.nixsolutions.domain.Role;

/**
 * An implementation RoleDao. This class uses a hibernate technology for work
 * with DB.
 * 
 * @author zinchenko
 * 
 */
public class HibernateRoleDao implements RoleDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private static RoleDao roleDao;

    /**
     * Get object of RoleDao as singleton.
     * 
     * @return object of RoleDao
     */
    public static RoleDao getRoleDao() {
        if (roleDao == null) {
            roleDao = new HibernateRoleDao();
        }
        return roleDao;
    }

    @Override
    public void create(Role role) {

        if (role == null) {
            throw new NullPointerException();
        }

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.save(role);

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
    public void update(Role role) {

        if (role == null) {
            throw new NullPointerException();
        }

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.update(role);

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
    public void remove(Role role) {

        if (role == null) {
            throw new NullPointerException();
        }

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.delete(role);

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
    public Role findByName(String nameRole) {

        if (nameRole == null) {
            throw new NullPointerException();
        }

        Role role = null;

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();

            Query query = session
                    .createQuery("FROM Role role WHERE role.name = :name");
            query.setString("name", nameRole);
            role = (Role) query.uniqueResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return role;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = null;

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();

            roles = session.createCriteria(Role.class).list();

            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return roles;
    }

}
