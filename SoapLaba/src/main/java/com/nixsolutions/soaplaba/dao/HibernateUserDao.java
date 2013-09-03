package com.nixsolutions.soaplaba.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.soaplaba.domain.User;

@Repository
public class HibernateUserDao implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void create(User user) {

		if (user == null) {
			throw new NullPointerException();
		}

		Session session = sessionFactory.getCurrentSession();
		session.save(user);

	}

	@Transactional
	public void update(User user) {

		if (user == null) {
			throw new NullPointerException();
		}

		Session session = sessionFactory.getCurrentSession();
		session.update(user);

	}

	@Transactional
	public void remove(User user) {

		if (user == null) {
			throw new NullPointerException();
		}

		Session session = sessionFactory.getCurrentSession();
		session.delete(user);

	}

	@Transactional
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
		@SuppressWarnings("unchecked")
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
