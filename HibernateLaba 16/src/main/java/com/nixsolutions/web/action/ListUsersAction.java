package com.nixsolutions.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nixsolutions.dao.HibernateUserDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;

/**
 * An action is invoked before render list of users.
 * 
 * @author zinchenko
 * 
 */
public class ListUsersAction implements Action {

    private static final Log LOG = LogFactory.getLog(ListUsersAction.class);

    private UserDao userDao = HibernateUserDao.getUserDao();

    public String perform(HttpServletRequest request,
            HttpServletResponse response) {
        
        LOG.trace("perform() invoke");

        request.setAttribute("allUsers", userDao.findAll());//allUsers);

        return "WEB-INF/pages/listUsers.jsp";
    }

}
