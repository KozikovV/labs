package com.nixsolutions.web.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.domain.User;
import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

/**
 * It validates email when user is edited.
 * 
 * @author zinchenko
 *
 */
public class ExistEmailForEditValidator extends FieldValidatorSupport {

    private static final Log LOG = LogFactory
            .getLog(ExistEmailForEditValidator.class);

    @Autowired
    private UserDao userDao;

    public void validate(Object object) throws ValidationException {

        String fieldName = getFieldName();
        String email = (String) getFieldValue(fieldName, object);
        LOG.debug("validation email: " + email);

        Long id = (Long) getFieldValue("userBean.id", object);
        LOG.debug("id: " + id);

        if (email.isEmpty()) {
            LOG.debug("email is empty");
            return;
        }

        User user = userDao.findByEmail(email);

        if (user != null) {
            if (user.getId() != id) {
                LOG.debug("user with e-mail: " + email + " exist. User: " + user
                        + " has this e-mail");
                addFieldError(fieldName, object);
            }
            LOG.debug("The e-mail didn't change.");
        }

    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
