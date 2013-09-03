package com.nixsolutions.soaplaba.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.soaplaba.domain.Role;

@Repository
public class HibernateRoleDao implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void create(Role role) {

		if (role == null) {
			throw new NullPointerException();
		}

		Session session = sessionFactory.getCurrentSession();
		session.save(role);

	}

	@Transactional
	public void update(Role role) {

		if (role == null) {
			throw new NullPointerException();
		}

		Session session = sessionFactory.getCurrentSession();
		session.update(role);

	}

	@Transactional
	public void remove(Role role) {

		if (role == null) {
			throw new NullPointerException();
		}

		Session session = sessionFactory.getCurrentSession();
		session.delete(role);

	}

	@Transactional(readOnly = true)
	public Role findByName(String nameRole) {

		if (nameRole == null) {
			throw new NullPointerException();
		}

		Role role = null;

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Role role WHERE role.name = ?");
		query.setString(0, nameRole);
		role = (Role) query.uniqueResult();

		return role;
	}

	@Transactional(readOnly = true)
	public List<Role> findAll() {

		List<Role> roles = sessionFactory.getCurrentSession()
				.createCriteria(Role.class).list();

		return roles;
	}

	@Transactional
	public Role findById(Long id) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from Role as role where role.id = ?");
		query.setLong(0, id);
		Role role = (Role) query.uniqueResult();

		return role;
	}

}
