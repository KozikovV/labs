package com.nixsolutions.web.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;
import com.nixsolutions.web.bean.UserBean;
import com.opensymphony.xwork2.Action;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/usersAndRoles.xml" })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class AdminActionTest {

	@Autowired
	private List<User> users;

	@Autowired
	private List<Role> roles;

	private AdminAction userAction;

	private UserDao userDao;

	private RoleDao roleDao;

	@Before
	public void before() {
		userAction = new AdminAction();

		userDao = mock(UserDao.class);
		userAction.setUserDao(userDao);

		roleDao = mock(RoleDao.class);
		userAction.setRoleDao(roleDao);

		doReturn(users).when(userDao).findAll();
		doReturn(users.get(0)).when(userDao).findByLogin(
				users.get(0).getLogin());

		doReturn(roles).when(roleDao).findAll();

	}

	@Test
	public void getUsers() {

		assertEquals(users, userAction.getUsers());

	}

	@Test
	public void getRoles() {

		assertEquals(roles, userAction.getRoles());

	}

	@Test
	public void addUser() {

		userAction.setUserBean(UserBean.UserToUserBean(users.get(0)));
		userAction.addUser();

		verify(userDao).create(any(User.class));

	}



	@Test
	public void editUserForm() {

		String login = users.get(0).getLogin();

		userAction.setLogin(login);
		assertEquals(Action.SUCCESS, userAction.editUserForm());

		verify(userDao).findByLogin(login);

		assertEquals(users.get(0),
				UserBean.UserBeanToUser(userAction.getUserBean()));

	}


	@Test
	public void removeUser() {

		Long id = users.get(0).getId();

		userAction.setId(id);
		assertEquals(Action.SUCCESS, userAction.removeUser());
		verify(userDao).remove(id);

	}

}
