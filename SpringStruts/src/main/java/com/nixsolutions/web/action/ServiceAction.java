package com.nixsolutions.web.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;
import com.nixsolutions.web.bean.UserBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.CustomValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

/**
 * 
 * The service action.
 * 
 * @author zinchenko
 * 
 */
@Namespace("/serviceroom")
@ResultPath("/")
public class ServiceAction extends ActionSupport implements ServletRequestAware {

	public static final String ADMIN = "admin";
	public static final String USER = "user";
	public static final String LOGIN = "login";

	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	private UserBean userBean = new UserBean();

	private static final Log LOG = LogFactory.getLog(ServiceAction.class);

	private HttpServletRequest request;

	/**
	 * Logout operation.
	 * 
	 * @return
	 */
	@Action(value = "logout", results = { @Result(name = "success", location = "disp", type = "redirect") })
	@SkipValidation
	public String logout() {

		LOG.debug("logout()");

		request.getSession().invalidate();

		return SUCCESS;
	}

	/**
	 * 
	 * The operation turns user to side of the application. It turns the user to
	 * the login page if the user hasn't authenticated yet. It turns the user to
	 * the admin room if the user's role is Admin. It turns the user to the user
	 * room if user's role is User. It throws exception if the user's role isn't
	 * know.
	 * 
	 * @return result
	 * 
	 * @return
	 */
	@Action(value = "disp", results = {
			@Result(name = LOGIN, type = "redirectAction", params = {
					"actionName", "login", "namespace", "/" }),
			@Result(name = ADMIN, type = "redirectAction", params = {
					"actionName", "listUsers", "namespace", "/adminroom" }),
			@Result(name = USER, type = "redirectAction", params = {
					"actionName", "hello", "namespace", "/userroom" }) })
	@SkipValidation
	public String disp() {

		String username = request.getRemoteUser();

		if (username == null) {
			LOG.debug("user has not autentifitated");
			return LOGIN;
		} else {
			if (request.isUserInRole(ROLE_ADMIN)) {
				LOG.debug("user (" + username + ") has role: " + ROLE_ADMIN);
				return ADMIN;
			} else if (request.isUserInRole(ROLE_USER)) {
				LOG.debug("user (" + username + ") has role: " + ROLE_USER);
				return USER;
			} else {
				LOG.error("Don't know this role");
				throw new RuntimeException("Don't know this role");
			}
		}

	}

	@Action(value = "registrationForm", results = { @Result(name = "success", location = "pages/registration.jsp") })
	@SkipValidation
	public String registrationForm() {
		return SUCCESS;
	}

	/**
	 * It registrations a user. The registration method content same logic as
	 * addUser(), but it is need to separate validation logic.
	 * 
	 * @return result
	 */
	@Action(value = "registration", results = {
			@Result(name = INPUT, location = "pages/registration.jsp"),
			@Result(name = SUCCESS, type = "redirectAction", params = {
					"actionName", "listUsers", "namespace", "/adminroom" }), })
	@Validations(customValidators = {
			@CustomValidator(fieldName = "userBean.login", type = "existLogin", key = "error.existLogin"),
			@CustomValidator(fieldName = "userBean.email", type = "existEmail", key = "error.existEmail"),
			@CustomValidator(fieldName = "userBean.password", type = "password", key = "error.password"),
			@CustomValidator(fieldName = "userBean.captcha", type = "captcha", key = "error.captcha") }, 
	requiredStrings = { 
			@RequiredStringValidator(fieldName = "userBean.passwordAgain", key = "error.required"),
			@RequiredStringValidator(fieldName = "userBean.captcha", key = "error.required"),})
	public String registration() {

		User user = UserBean.UserBeanToUser(userBean);
		LOG.debug("registration user : " + user);

		userDao.create(user);

		return SUCCESS;
	}

	/**
	 * Get all roles.
	 * 
	 * @return
	 */
	public List<Role> getRoles() {

		List<Role> roles = roleDao.findAll();
		LOG.debug("all roles: " + roles);

		return roles;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@VisitorFieldValidator(key = "error")
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
