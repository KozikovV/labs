package com.nixsolutions.web.filter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
 * The filter checks data which user input into fields on page. This filter
 * invoke before adding and editing user.
 * 
 * @author zinchenko
 * 
 */
public class AddUserFilter implements Filter {

    private RoleDao roleDao = JdbcRoleDao.getRoleDao();
    private UserDao userDao = JdbcUserDao.getUserDao();

    private HttpServletRequest request;
    private HttpServletResponse response;

    private FilterConfig filterConfig;

    private Log LOG = LogFactory.getLog(AddUserFilter.class);

    private String id;
    private String login;
    private String password;
    private String passwordAgain;
    private String email;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String role;

    private List<String> errors;

    private User user;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        LOG.trace("DoFilter()");

        request = (HttpServletRequest) servletRequest;
        response = (HttpServletResponse) servletResponse;

        initValues();
        checkId();
        checkLoggin();
        checkPassword();
        checkEmail();
        checkFirstName();
        checkLastName();
        checkBirthDate();

        user.setRole(new Role(Long.parseLong(role)));

        if (errors.isEmpty()) {
            LOG.trace("errors is empty. User: " + user);

            // userDao.create(user);
            request.setAttribute("user", user);

            chain.doFilter(request, response);
            // request.getRequestDispatcher(Url.LIST_USERS).forward(request,
            // response);
        } else {
            List<Role> roles = roleDao.findAll();
            request.setAttribute("roles", roles);
            request.setAttribute("errors", errors);
            if (id.isEmpty()) {
                request.getRequestDispatcher(Url.ADD_NEW_USER).forward(request,
                        response);
            } else {
                request.getRequestDispatcher(Url.EDIT_USER).forward(request,
                        response);
            }

        }

    }

    /**
     * Initializes the values.
     */
    private void initValues() {

        errors = new ArrayList<String>();
        user = new User();
        request.setAttribute("user", user);

        id = request.getParameter("id");
        login = request.getParameter("login");
        password = request.getParameter("password");
        passwordAgain = request.getParameter("passwordAgain");
        email = request.getParameter("email");
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        birthDate = request.getParameter("birthDate");
        role = request.getParameter("role");

        LOG.trace("login=" + login + " password=" + password + " pasAg="
                + passwordAgain + " email=" + email + " firstName=" + firstName
                + " lastName=" + lastName + " birtDate" + birthDate + " role="
                + role);

    }

    /**
     * Checks the id.
     */
    private void checkId() {
        if (!id.isEmpty()) {
            user.setId(Long.parseLong(id));
        }
    }

    /**
     * Checks the loggin.
     */
    private void checkLoggin() {
        if (login.isEmpty()) {
            LOG.trace("Login is empty!");
            errors.add("Login is empty!");
        } else if (userDao.isUserByLogin(login) && id.isEmpty()) {
            LOG.trace("user with login: " + login + " already exist");
            errors.add("User with this login exist!");
            request.setAttribute("errors", errors);
        } else {
            user.setLogin(login);
        }
    }

    /**
     * Checks the passwords.
     */
    private void checkPassword() {
        if (password.isEmpty()) {
            LOG.trace("First password is empty!");
            errors.add("First password is empty!");
        }
        if (passwordAgain != null && passwordAgain.isEmpty()) {
            LOG.trace("Second  password is empty!");
            errors.add("Second password is empty!");
        } else if (!password.equals(passwordAgain) && id.isEmpty()) {
            LOG.trace("passwords are not equal");
            errors.add("Check your password!");
        } else {
            user.setPassword(password);
        }
    }

    /**
     * Checks the e-mail.
     */
    private void checkEmail() {
        if (email.isEmpty()) {
            LOG.trace("E-mail is empty!");
            errors.add("E-mail is empty!");
        } else if (userDao.isUserByEmail(email) && id.isEmpty()) {
            LOG.trace("User with this enail exist");
            errors.add("Use other e-mail!");
        } else {
            user.setEmail(email);
        }
    }

    /**
     * Checks the first name.
     */
    private void checkFirstName() {
        if (firstName.isEmpty()) {
            LOG.trace("First name is empty!");
            errors.add("First name is empty!");
        } else {
            user.setFirstName(firstName);
        }
    }

    /**
     * Checks the last name.
     */
    private void checkLastName() {
        if (lastName.isEmpty()) {
            LOG.trace("Last name is empty!");
            errors.add("Last name is empty!");
        } else {
            user.setLastName(lastName);
        }
    }

    /**
     * Checks the birthday.
     */
    private void checkBirthDate() {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        if (birthDate.isEmpty()) {
            LOG.trace("Birth date is empty!");
            errors.add("Birth date is empty!");
        } else if (!birthDate.matches(regex)) {
            LOG.trace("Date isnt true! For exaple 2013-02-21.");
            errors.add("Last name is empty!");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(birthDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            user.setBirthDate(date);
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
