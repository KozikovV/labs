package com.nixsolutions.web.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nixsolutions.api.UserApi;
import com.nixsolutions.domain.User;
import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

/**
 * It validates login when user is added.
 * 
 * @author zinchenko
 * 
 */
public class ExistLoginValidator extends FieldValidatorSupport {

	private static final Log LOG = LogFactory.getLog(ExistLoginValidator.class);

	@Autowired
	private UserApi userApi;

	public void validate(Object object) throws ValidationException {

		String fieldName = getFieldName();
		String login = (String) getFieldValue(fieldName, object);
		LOG.debug("validation login: " + login);

		if (login == null || login.isEmpty()) {

			LOG.debug("login is empty or == null, login : " + login);
			return;
		}

		User user = userApi.findByLogin(login);

		LOG.debug("found user by login: " + user);

		if (user != null) {
			LOG.debug("user with login: " + login + " exist. User: " + user
					+ " has this e-mail");
			addFieldError(fieldName, object);
		}

	}

	public void setUserApi(UserApi userApi) {
		this.userApi = userApi;
	}

}
