package com.nixsolutions.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nixsolutions.dao.HibernateRoleDao;
import com.nixsolutions.dao.HibernateUserDao;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;
import com.nixsolutions.web.Url;

/**
 * This action is invoked before render edit user page. It gets user by id from
 * DB and put it to the request object as attribute.
 * 
 * @author zinchenko
 * 
 */
public class EditUserFormAction implements Action {

    private RoleDao roleDao = HibernateRoleDao.getRoleDao();
    private UserDao userDao = HibernateUserDao.getUserDao();

    private static final Log LOG = LogFactory.getLog(EditUserFormAction.class);

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {

        String id = request.getParameter("id");

        User user = userDao.findById(Long.parseLong(id));

        List<Role> roles = roleDao.findAll();

        request.setAttribute("roles", roles);
        request.setAttribute("user", user);

        return Url.EDIT_USER;
    }

}
