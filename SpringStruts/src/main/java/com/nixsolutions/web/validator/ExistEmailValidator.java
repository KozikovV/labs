package com.nixsolutions.web.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.domain.User;
import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;


/**
 * It validates email when user is added.
 * 
 * @author zinchenko
 *
 */
public class ExistEmailValidator extends FieldValidatorSupport {

	private static final Log LOG = LogFactory.getLog(ExistEmailValidator.class);

	@Autowired
	private UserDao userDao;

	public void validate(Object object) throws ValidationException {
		
		String fieldName = getFieldName();
		String email = (String) getFieldValue(fieldName, object);
		LOG.debug("validation email: "+email);

		if (email == null || email.isEmpty()) {
			LOG.debug("email is empty");
			return;
		}
		
		User user = userDao.findByEmail(email);

		if (user != null) {
			LOG.debug("user with e-mail: " + email + " exist. User: " + user
					+ " has this e-mail");
			addFieldError(fieldName, object);
		}

	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
