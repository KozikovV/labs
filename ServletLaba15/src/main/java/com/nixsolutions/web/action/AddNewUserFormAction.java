package com.nixsolutions.web.action;

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

/**
 * An action for forward to the new user page. Before forwarding load all roles
 * from DB ant put to the request object as attribute.
 * 
 * @author zinchenko
 * 
 */
public class AddNewUserFormAction implements Action {

    private RoleDao roleDao = JdbcRoleDao.getRoleDao();

    private Log LOG = LogFactory.getLog(AddNewUserFormAction.class);

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {

        LOG.trace("perform() invoked");

        List<Role> roles = roleDao.findAll();
        LOG.trace(roles);

        request.setAttribute("roles", roles);

        return "WEB-INF/pages/addNewUser.jsp";

    }

}
