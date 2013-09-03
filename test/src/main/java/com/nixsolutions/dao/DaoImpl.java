package com.nixsolutions.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.domain.Person;
import com.nixsolutions.domain.User;

@Component(value = "dao")
public class DaoImpl {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public List<User> findUsersBySearch(List<String> request) {
		List<User> users = new ArrayList<User>();

		return users;
	}

	@Transactional
	public List<User> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(User.class)
				.list();
	}

	@Transactional
	public List<Person> findAllPersons() {
		return sessionFactory.getCurrentSession().createCriteria(Person.class)
				.list();
	}

	@Transactional
	public Person getPersonById(Long id) {
		return (Person) sessionFactory.getCurrentSession()
				.createQuery("from Person person where person.id = :person_id")
				.setLong("person_id", id).uniqueResult();
	}
	
	@Transactional
	public Person getWorkerByIdAsPerson(Long id) {
		return (Person) sessionFactory.getCurrentSession()
				.createQuery("from Worker w where w.id = :w_id")
				.setLong("w_id", id).uniqueResult();
	}
	
	@Transactional
	public Person getTeacherByIdAsPerson(Long id) {
		return (Person) sessionFactory.getCurrentSession()
				.createQuery("from Teacher t where t.id = :t_id")
				.setLong("t_id", id).uniqueResult();
	}

}
