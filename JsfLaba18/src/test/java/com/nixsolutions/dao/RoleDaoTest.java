package com.nixsolutions.dao;

import static junit.framework.Assert.*;

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
public class RoleDaoTest {
	
	@Autowired
	private RoleDao roleDao;

	private Role[] roles = new Role[2];

	@Before
	public void before() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		roles[0] = new Role(101L, "newRole1");
		roles[1] = new Role(102L, "newRole2");
	}


	@Test
	@DatabaseSetup("classpath:dataset.xml")
	public void testSaveRole() throws Exception {

		try {
			 roleDao.create(null);
			 fail("should be threw NullPointerException when pass null");
		} catch (NullPointerException e) {
		} catch (Exception e) {
			 fail("should be threw NullPointerException when pass null");
		}

		roleDao.create(roles[0]);
		Role role = roleDao.findByName(roles[0].getName());
		List<Role> all = roleDao.findAll();
		assertEquals(roles[0].getName(), role.getName());

	}
	
	@Test
	@DatabaseSetup("classpath:dataset.xml")
	public void testUpdateRole() throws Exception {
		try {
			roleDao.update(null);
			fail("should be throw NullPointerException if role null");
		} catch (NullPointerException e) {
		} catch (Exception e) {
			fail("should be throw NullPointerException if role null");
		}

		try {
			roleDao.update(roles[0]);
			fail("should be throw Exception if role " + "doesn't exist");
		} catch (Exception e) {

		}

		Role roleForUpdate = new Role(1L, roles[0].getName());

		roleDao.update(roleForUpdate);
		Role role = roleDao.findByName(roleForUpdate.getName());
		assertEquals(roleForUpdate, role);
	}

	@Test
	@DatabaseSetup("classpath:dataset.xml")
	public void testFindByName() throws Exception {
		try {
			roleDao.findByName(null);
			fail("should be throw NullPointerException if role null");
		} catch (NullPointerException e) {
		} catch (Exception e) {
			fail("should be throw NullPointerException if role null");
		}

		assertNull(roleDao.findByName(roles[0].getName()));

		Role expectedRole = roleDao.findAll().get(0);
		Role role = roleDao.findByName(expectedRole.getName());

		assertEquals(expectedRole, role);
	}

	@Test
	@DatabaseSetup("classpath:dataset.xml")
	public void testDeleteRole() throws Exception {

		try {
			roleDao.remove(null);
			fail("should be throw NullPointerException if role null");
		} catch (NullPointerException e) {
		} catch (Exception e) {
			fail("should be throw NullPointerException if role null");
		}

		try {
			Role referencedRole = roleDao.findAll().get(1);
			roleDao.remove(referencedRole);
			fail("should be throw Exception if role "
					+ "referenced with any user(s)");
		} catch (Exception e) {

		}

		Role unreferencedRole = roleDao.findAll().get(2);

		assertEquals(unreferencedRole,
				roleDao.findByName(unreferencedRole.getName()));

		roleDao.remove(unreferencedRole);

		assertNull(roleDao.findByName(unreferencedRole.getName()));

	}


}
