package com.nixsolutions.web.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
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
    private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

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
	@Validations(customValidators = {
			@CustomValidator(fieldName = "userBean.email", type = "existEmailForEdit", key = "error.existEmail") })
	public String editUser() {

		User user = UserBean.UserBeanToUser(userBean);

		LOG.debug("Edited user: " + user);

		userDao.update(user);

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
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

}
