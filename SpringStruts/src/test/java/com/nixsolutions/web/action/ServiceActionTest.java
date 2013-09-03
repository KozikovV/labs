package com.nixsolutions.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.domain.User;
import com.nixsolutions.web.bean.UserBean;
import com.opensymphony.xwork2.Action;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/usersAndRoles.xml" })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class ServiceActionTest {

    private ServiceAction serviceAction;

    private HttpServletRequest request;
    private HttpSession session;
    
    private UserDao userDao;
    
    @Autowired
    private List<User> users;

    @Before
    public void before() {

        serviceAction = new ServiceAction();

        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        doReturn(session).when(request).getSession();

        serviceAction.setServletRequest(request);
        
        userDao = mock(UserDao.class);
        doReturn(users).when(userDao).findAll();
        doReturn(users.get(0)).when(userDao).findByLogin(
                users.get(0).getLogin());
        
        serviceAction.setUserDao(userDao);

    }

    @Test
    public void logout() {

        serviceAction.logout();

        verify(session, times(1)).invalidate();

    }

    @Test
    public void dispRoleAdmin() {

        doReturn(ServiceAction.ROLE_ADMIN).when(request).getRemoteUser();
        doReturn(true).when(request).isUserInRole(ServiceAction.ROLE_ADMIN);

        assertEquals(ServiceAction.ADMIN, serviceAction.disp());

        verify(request).getRemoteUser();
        verify(request).isUserInRole(ServiceAction.ROLE_ADMIN);

    }

    @Test
    public void dispRoleUser() {

        doReturn(ServiceAction.ROLE_USER).when(request).getRemoteUser();
        doReturn(true).when(request).isUserInRole(ServiceAction.ROLE_USER);

        assertEquals(ServiceAction.USER, serviceAction.disp());

        verify(request).getRemoteUser();
        verify(request).isUserInRole(ServiceAction.ROLE_USER);

    }

    @Test
    public void dispRoleNotUser() {

        doReturn(null).when(request).getRemoteUser();
        doReturn(false).when(request).isUserInRole(anyString());

        assertEquals(ServiceAction.LOGIN, serviceAction.disp());

        verify(request).getRemoteUser();
        verify(request, never()).isUserInRole(anyString());

    }

    @Test
    public void dispRoleWrongRole() {

        doReturn("ROLE_XXXXXX").when(request).getRemoteUser();
        doReturn(false).when(request).isUserInRole(anyString());

        try {
            serviceAction.disp();
            fail("must be thrown Exception");
        } catch (Exception e) {
        }

        verify(request).getRemoteUser();
        verify(request, atMost(2)).isUserInRole(anyString());

    }

    @Test
    public void registration() {

        serviceAction.setUserBean(UserBean.UserToUserBean(users.get(0)));
        assertEquals(Action.SUCCESS, serviceAction.registration());

        verify(userDao).create(any(User.class));

    }

}
