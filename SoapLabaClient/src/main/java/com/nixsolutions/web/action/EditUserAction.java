package com.nixsolutions.web.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.nixsolutions.api.RoleApi;
import com.nixsolutions.api.UserApi;
import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;
import com.nixsolutions.web.bean.UserBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.CustomValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@Namespace("/adminroom")
@ResultPath("/")
public class EditUserAction extends ActionSupport {

	@Autowired
	private RoleApi roleApi;

	@Autowired
	private UserApi userApi;

	private static final Log LOG = LogFactory.getLog(AdminAction.class);

	private UserBean userBean = new UserBean();

	/**
	 * Edit user.
	 * 
	 * @return
	 */
	@Action(value = "editUser", results = {
			@Result(name = "input", location = "pages/editUser.jsp"),
			@Result(name = "success", location = "listUsers", type = "redirectAction") })
	@Validations(customValidators = { @CustomValidator(fieldName = "userBean.email", type = "existEmailForEdit", key = "error.existEmail") })
	public String editUser() {

		User user = UserBean.UserBeanToUser(userBean);

		LOG.debug("Edited user: " + user);

		userApi.update(user);

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

	public void setUserApi(UserApi userApi) {
		this.userApi = userApi;
	}

	@VisitorFieldValidator(key = "error")
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public void setRoleApi(RoleApi roleApi) {
		this.roleApi = roleApi;
	}

}
