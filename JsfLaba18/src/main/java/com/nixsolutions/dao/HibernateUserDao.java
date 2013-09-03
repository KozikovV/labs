package com.nixsolutions.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.domain.User;

public class HibernateUserDao implements UserDao {

	
	private SessionFactory sessionFactory;

	public HibernateUserDao() {

	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void create(User user) {

		if (user == null) {
			throw new NullPointerException();
		}

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();

	}

	@Transactional
	public void update(User user) {

		if (user == null) {
			throw new NullPointerException();
		}

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();

	}

	@Transactional
	public void remove(User user) {

		if (user == null) {
			throw new NullPointerException();
		}

		sessionFactory.getCurrentSession().delete(user);
	}

	public void remove(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("DELETE FROM User user WHERE user.id = ?");
		query.setLong(0, id);
		query.executeUpdate();

	}

	@Transactional(readOnly = true)
	public List<User> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<User> users = session.createCriteria(User.class).list();
		return users;
	}

	@Transactional
	public User findByLogin(String login) {

		if (login == null) {
			throw new NullPointerException();
		}

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from User as user where user.login = ?");
		query.setString(0, login);
		User user = (User) query.uniqueResult();

		return user;
	}

	@Transactional
	public User findByEmail(String email) {

		if (email == null) {
			throw new NullPointerException();
		}

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from User as user where user.email = ?");
		query.setString(0, email);
		User user = (User) query.uniqueResult();

		return user;
	}

}
