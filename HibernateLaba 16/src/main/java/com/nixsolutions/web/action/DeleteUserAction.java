package com.nixsolutions.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nixsolutions.dao.HibernateUserDao;
import com.nixsolutions.dao.UserDao;

/**
 * An Action which remove user. The user is removed by id which pass as
 * parameter of the request.
 * 
 * @author zinchenko
 * 
 */
public class DeleteUserAction implements Action {
    
    private static UserDao userDao = HibernateUserDao.getUserDao();

    private static final Log LOG = LogFactory.getLog(DeleteUserAction.class);

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {
        
        Long id = Long.parseLong(request.getParameter("id"));
        
        userDao.remove(id);

        return "listUsers.htm";
    }

}
