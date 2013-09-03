package com.nixsolutions.web.action;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nixsolutions.dao.JdbcRoleDao;
import com.nixsolutions.dao.JdbcUserDao;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.domain.User;
import com.nixsolutions.web.Url;

/**
 * An edit user action.
 * 
 * @author zinchenko
 *
 */
public class EditUserAction implements Action {
    
    
    private UserDao userDao = JdbcUserDao.getUserDao();

    private static final Log LOG = LogFactory.getLog(EditUserAction.class);

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {
        
        LOG.trace("invoke perform()");
        
        User user = (User) request.getAttribute("user");

        LOG.trace(user);

        userDao.update(user);
        
        return Url.LIST_USERS;
    }



}
