package com.nixsolutions.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nixsolutions.dao.JdbcUserDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;
import com.nixsolutions.web.filter.Principal;

/**
 * An action is invoked when user put his login, password to the field of login
 * page and submit login button.
 * 
 * @author zinchenko
 * 
 */
public class LoginAction implements Action {

    private static final Log LOG = LogFactory.getLog(LogoutAction.class);

    private UserDao userDao = JdbcUserDao.getUserDao();

    private String username;
    private String password;

    private List<String> errors;

    private HttpServletRequest request;
    private HttpServletResponse response;

    private User user;

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {

        this.request = request;
        this.response = response;

        errors = new ArrayList<String>();

        username = (String) request.getParameter("username");
        password = (String) request.getParameter("password");

        LOG.trace("perform() with: username = " + username + "; password = "
                + password);

        if (username == null || password == null) {
            return "/loginPage.jsp";
        }

        validateForEmpty();

        if (!errors.isEmpty()) {
            return "/loginPage.jsp";
        } else {
            validateUser();
            if (!errors.isEmpty()) {
                return "/loginPage.jsp";
            } else {
                Role role = user.getRole();
                Principal principal = new Principal(username, role.getName());

                HttpSession session = request.getSession();
                session.setAttribute(Principal.PRINCIPAL, principal);
                return "/index.jsp";
            }
        }

    }

    private void validateForEmpty() {
        if (username.isEmpty()) {
            errors.add("Username is empty!");
        }
        if (password.isEmpty()) {
            errors.add("Password is empty!");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
        }
    }

    private void validateUser() {

        if (!userDao.isUserByLogin(username)) {
            errors.add("User with this username is not exist!");
            request.setAttribute("errors", errors);
        } else {
            user = userDao.findByLogin(username);

            if (!password.equals(user.getPassword())) {
                errors.add("Password is wrong!");
                request.setAttribute("errors", errors);
            }
        }

    }

}
