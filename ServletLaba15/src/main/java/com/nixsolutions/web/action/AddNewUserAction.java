package com.nixsolutions.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nixsolutions.dao.JdbcRoleDao;
import com.nixsolutions.dao.JdbcUserDao;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;
import com.nixsolutions.web.Url;

/**
 * Action for adding new user.
 * 
 * @author zinchenko
 *
 */
public class AddNewUserAction implements Action {

   
    private UserDao userDao = JdbcUserDao.getUserDao();

    private static final Log LOG = LogFactory.getLog(AddNewUserAction.class);

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {

        LOG.trace("invoke perform()");

        User user = (User) request.getAttribute("user");

        LOG.trace(user);

        userDao.create(user);

        return Url.LIST_USERS;
    }

}