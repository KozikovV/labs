package com.nixsolutions.web.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.nixsolutions.api.RoleApi;
import com.nixsolutions.api.UserApi;
import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;
import com.nixsolutions.web.bean.UserBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.CustomValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * 
 * Action which works with user entity.
 * 
 * @author zinchenko
 * 
 */
@Namespace("/adminroom")
@ResultPath("/")
public class AdminAction extends ActionSupport {

	@Autowired
	private UserApi userApi;

	@Autowired
	private RoleApi roleApi;

	private String login;

	private Long id;

	private static final Log LOG = LogFactory.getLog(AdminAction.class);

	private UserBean userBean = new UserBean();

	@Override
	@Actions(value = {
			@Action(value = "addUserForm", results = { @Result(name = "success", location = "pages/addUser.jsp") }),
			@Action(value = "listUsers", results = { @Result(name = "success", location = "pages/listUsers.jsp") }) })
	@SkipValidation
	public String execute() throws Exception {
		return super.execute();
	}

	/**
	 * Return list of users.
	 * 
	 * @return
	 */
	public List<User> getUsers() {
		
		List<User> users = userApi.findAll(); 

		return users;
	}

	/**
	 * 
	 * It adds a user.
	 * 
	 * @return result
	 */
	@Action(value = "addUser", results = {
			@Result(name = "input", location = "pages/addUser.jsp"),
			@Result(name = "success", location = "listUsers", type = "redirectAction") })
	@Validations(customValidators = {
			@CustomValidator(fieldName = "userBean.login", type = "existLogin", key = "error.existLogin"),
			@CustomValidator(fieldName = "userBean.email", type = "existEmail", key = "error.existEmail"),
			@CustomValidator(fieldName = "userBean.password", type = "password", key = "error.password") }, requiredStrings = { @RequiredStringValidator(fieldName = "userBean.passwordAgain", key = "error.required") })
	public String addUser() {

		User user = UserBean.UserBeanToUser(userBean);
		LOG.debug("user for add: " + user);

		userApi.create(user);

		return SUCCESS;
	}

	/**
	 * It invokes before rendering edit page.
	 * 
	 * @return result
	 */
	@Action(value = "editUserForm", results = { @Result(name = "success", location = "pages/editUser.jsp") })
	@SkipValidation
	public String editUserForm() {

		LOG.debug("login of user : " + login);

		User user = userApi.findByLogin(login);
		LOG.debug("user : " + user);

		userBean = UserBean.UserToUserBean(user);

		return SUCCESS;
	}

	/**
	 * Remove user.
	 * 
	 * @return
	 */
	@Action(value = "removeUser", results = { @Result(name = "success", location = "listUsers", type = "redirect") })
	@SkipValidation
	public String removeUser() {

		LOG.debug("remove user vith id : " + id);

		userApi.remove(id);

		return SUCCESS;
	}

	/**
	 * Get all roles.
	 * 
	 * @return
	 */
	public List<Role> getRoles() {

		List<Role> roles = roleApi.findAll();
		LOG.debug("all roles: " + roles);

		return roles;
	}

	//
	//
	// GET & SET
	//
	//

	public void setUserApi(UserApi userApi) {
		this.userApi = userApi;
	}

	public void setRoleApi(RoleApi roleApi) {
		this.roleApi = roleApi;
	}

	@VisitorFieldValidator(key = "error")
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
