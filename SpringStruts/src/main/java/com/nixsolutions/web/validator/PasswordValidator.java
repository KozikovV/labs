package com.nixsolutions.web.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;


/**
 * 
 * It validate the passwords for equals.
 * 
 * @author zinchenko
 *
 */
public class PasswordValidator extends FieldValidatorSupport {

	private static final Log LOG = LogFactory.getLog(PasswordValidator.class);
	private static final String PASSWORD_AGAIN_FIELD_NAME = "userBean.passwordAgain";

	public void validate(Object object) throws ValidationException {

		String fieldName = getFieldName();
		String password = (String) getFieldValue(fieldName, object);
		String passwordAgain = (String) getFieldValue(
				PASSWORD_AGAIN_FIELD_NAME, object);

		LOG.debug("password: " + password);
		LOG.debug("passwordAgain: " + passwordAgain);
		
		if (password.isEmpty() || passwordAgain.isEmpty()) {
			return;
		}

		if (!password.equals(passwordAgain)) {
			addFieldError(fieldName, object);
			addFieldError(PASSWORD_AGAIN_FIELD_NAME, object);
		}

	}

}
