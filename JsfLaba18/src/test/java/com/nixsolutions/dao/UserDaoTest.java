package com.nixsolutions.dao;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContextTest.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class UserDaoTest {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;

	private Role[] roles = new Role[2];
	private User[] users = new User[2];


	@Before
	public void before() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		roles[0] = new Role(101L, "newRole1");
		roles[1] = new Role(102L, "newRole2");
		users[0] = new User(101L, "newFN1", "newLN1", "log1dfyuk",
				"qwe@asd.as", "pass1", format.parse("2000-07-12"), new Role(2L,
						"newRole222"));
		users[1] = new User(102L, "newFN2", "newLN2", "log2dfgs",
				"asd@fgc.asd", "pass2", format.parse("2011-07-12"), new Role(
						3L, "newRole2333"));

	}

	@Test
	@DatabaseSetup("classpath:dataset.xml")
	public void testGetAllUsers() throws Exception {
		List<User> users = userDao.findAll();
		assertEquals(2, users.size());
	}

	@Test
	@DatabaseSetup("classpath:dataset.xml")
	public void testSaveUser() {

		try {
			userDao.create(null);
			fail("should be threw NullPointerException when pass null");
		} catch (NullPointerException e) {
		} catch (Exception e) {
			fail("should be threw NullPointerException when pass null");
		}
		
		List<User> u = userDao.findAll();

		userDao.create(users[0]);
		assertEquals(u.size() + 1, userDao.findAll().size());

	}

	@Test
	@DatabaseSetup("classpath:dataset.xml")
	public void testUpdateUser() throws Exception {

		try {
			userDao.update(null);
			fail("should be throw NullPointerException if role null");
		} catch (NullPointerException e) {
		} catch (Exception e) {
			fail("should be throw NullPointerException if role null");
		}

		try {
			userDao.update(users[0]);
			fail("should be throw Exception if role " + "doesn't exist");
		} catch (Exception e) {

		}

		
		User expectedUser = userDao.findAll().get(0);
		expectedUser.setLogin("llooggiinn");
		userDao.update(expectedUser);
		User user = userDao.findAll().get(0);

		assertEquals(expectedUser, user);

	}

	@Test
	@DatabaseSetup("classpath:dataset.xml")
	public void testGetUserByLogin() throws Exception {

		try {
			userDao.findByLogin(null);
			fail("should be throw NullPointerException if put null");
		} catch (NullPointerException e) {
		} catch (Exception e) {
			fail("should be throw NullPointerException if put null");
		}

		assertNull(userDao.findByLogin(users[1].getLogin()));

		User expectedUser = userDao.findAll().get(0);

		User user = userDao.findByLogin(expectedUser.getLogin());
		assertEquals(expectedUser, user);
	}

	@Test
	@DatabaseSetup("classpath:dataset.xml")
	public void testGetUserByEmail() throws Exception {

		try {
			userDao.findByEmail(null);
			fail("should be throw NullPointerException if put null");
		} catch (NullPointerException e) {
		} catch (Exception e) {
			fail("should be throw NullPointerException if put null");
		}

		assertNull(userDao.findByEmail(users[1].getEmail()));

		User expectedUser = userDao.findAll().get(0);

		User user = userDao.findByEmail(expectedUser.getEmail());
		assertEquals(expectedUser, user);
	}

	@Test
	@DatabaseSetup("classpath:dataset.xml")
	public void testDeleteUser() throws Exception {

		try {
			User user = null;
			userDao.remove(user);
			fail("should be throw NullPointerException if pass null");
		} catch (NullPointerException e) {
		} catch (Exception e) {
			fail("should be throw NullPointerException if pass null");
		}

		try {
			userDao.remove(users[0]);
			fail("should be throw IllegalArgumentException if user with "
					+ "this id doesn't exist");
		} catch (Exception e) {

		}
		List<User> u = userDao.findAll();
		
		userDao.remove(userDao.findAll().get(1));
		
		assertEquals(u.size()-1, userDao.findAll().size());

	}

}
