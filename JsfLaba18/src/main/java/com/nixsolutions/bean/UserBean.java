package com.nixsolutions.bean;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;
import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import com.sun.faces.util.MessageFactory;


/**
 * User backing bean.
 * 
 * @author zinchenko
 * 
 */
public class UserBean {


    /**
     * DAO of Users
     */
    private UserDao userDao;


    /**
     * DAO of Role
     */
    private RoleDao roleDao;

    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String password;
    private String passwordAgain;
    private Date birthDate;
    private Long role;

    private String captcha;

    /**
     * Registration method.
     * 
     * @return view to redirect.
     */
    public String registration() {
        boolean wasError = false;
        role = 1L;

        if (!validateLogin()) {
            addErrorMessage("login", "User with this login exist!");
            wasError = true;
        }

        if (!validateEmail()) {
            addErrorMessage("email", "User with this email exist!");
            wasError = true;
        }

        if (!validatePasswords()) {
            addErrorMessage("password", "Check your passwords!");
            wasError = true;
        }

        if (!checkCaptcha()) {
            addErrorMessage("captcha", "Check your captcha!");
            wasError = true;
        }

        if (wasError) {
            return null;
        }

        User user = packageUser();

        userDao.create(user);
        return "login.jsf";
    }

    /**
     * Edit method.
     * 
     * @return view to redirect
     */
    public String edit() {
        boolean wasError = false;

        if (!validateOtherEmail()) {
            addErrorMessage("email", "User with this email exist!");
            wasError = true;
        }

        if (!checkCaptcha()) {
            addErrorMessage("captcha", "Check your captcha!");
            wasError = true;
        }

        if (wasError) {
            return null;
        }

        User user = packageUser();

        userDao.update(user);

        return "listUsers";
    }

    /**
     * Method is invoked before edit page.
     * 
     * @param user
     *            user for edit
     * @return
     */
    public String goEdit(User user) {

        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        login = user.getLogin();
        email = user.getEmail();
        birthDate = user.getBirthDate();
        role = user.getRole().getId();

        return "editUser";
    }

    /**
     * Method for add user.
     * 
     * @return view name
     */
    public String add() {
        boolean wasError = false;

        if (!validateLogin()) {
            addErrorMessage("login", "User with this login exist!");
            wasError = true;
        }

        if (!validateEmail()) {
            addErrorMessage("email", "User with this email exist!");
            wasError = true;
        }

        if (!validatePasswords()) {
            addErrorMessage("password", "Passwords are not equals!");
            wasError = true;
        }

        if (!checkCaptcha()) {
            addErrorMessage("captcha", "Check your captcha!");
            wasError = true;
        }

        if (wasError) {
            return null;
        }

        User user = packageUser();

        userDao.create(user);

        return "listUsers";
    }

    /**
     * Create new User object use fields of this backing bean.
     * 
     * @return
     */
    private User packageUser() {
        Role r = roleDao.findById(role);

        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setBirthDate(birthDate);
        user.setRole(r);
        return user;

    }

    /**
     * Add new error message.
     * 
     * @param messageId
     *            ID of element
     * @param message
     *            message
     */
    private void addErrorMessage(String messageId, String message) {
        FacesContext.getCurrentInstance().addMessage(messageId,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    /**
     * It validates user.
     * 
     * @return {@code true} if login is correct
     */
    private boolean validateLogin() {

        User user = userDao.findByLogin(login);
        if (user != null) {
            return false;
        }

        return true;
    }

    /**
     * It validates e-mail.
     * 
     * @return {@code true} if e-mail is correct
     */
    private boolean validateEmail() {

        User user = userDao.findByEmail(email);
        if (user != null) {
            return false;
        }

        return true;
    }

    /**
     * It validates e-mail.
     * 
     * @return {@code true} if e-mail is correct
     */
    private boolean validateOtherEmail() {

        User user = userDao.findByEmail(email);
        if (user == null) {
            return true;
        }
        if (user.getId().equals(id)) {
            return true;
        }

        return false;
    }

    /**
     * It validates password.
     * 
     * @return {@code true} if password is correct
     */
    private boolean validatePasswords() {
        if (!password.equals(passwordAgain)) {
            return false;
        }
        return true;
    }

    /**
     * Get all roles.
     * 
     * @return all roles
     */
    public List<Role> getAllRoles() {

        List<Role> allRoles = roleDao.findAll();

        return allRoles;
    }

    /**
     * Set DAO for user.
     * 
     * @param userDao
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Set DAO for role.
     * 
     * @param roleDao
     */
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    /**
     * Get users.
     * 
     * @return
     */
    public List<User> getUsers() {

        List<User> users = userDao.findAll();

        return users;
    }

    /**
     * Remove user.
     * 
     * @param user
     *            user for removed
     * @return view to redirect
     */
    public String remove(User user) {

        userDao.remove(user);

        return "listUsers";
    }

    /**
     * Check input captcha value.
     * 
     * @return {@code true } if captcha value is correct
     */
    private boolean checkCaptcha() {
        HttpServletRequest request = getHttpServletRequest();
        boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(
                request, captcha);
        return captchaPassed;
    }

    /**
     * Get object of class HttpServletRequest
     * 
     * @return object of class HttpServletRequest
     */
    private HttpServletRequest getHttpServletRequest() {
        Object object = FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        if (object instanceof HttpServletRequest) {
            return (HttpServletRequest) object;
        } else {
            throw new Error(" request must be instance of HttpServletRequest.");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
