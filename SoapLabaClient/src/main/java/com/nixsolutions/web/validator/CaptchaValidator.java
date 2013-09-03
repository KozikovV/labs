package com.nixsolutions.web.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.google.code.kaptcha.Constants;
import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;


/**
 * 
 * It validates captcha value.
 * 
 * @author zinchenko
 *
 */
public class CaptchaValidator extends FieldValidatorSupport{

    private static final Log LOG = LogFactory.getLog(CaptchaValidator.class);
    
    public void validate(Object object) throws ValidationException {
        
        String fieldName = getFieldName();
        String captcha =  (String) getFieldValue(fieldName, object);
        LOG.debug("captcha value : "+captcha);
        
        if (captcha == null || captcha.isEmpty()) {
			LOG.debug("captcha value is null or empty, captcha : " + captcha);
        	return;
		}
        
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        
        String captchaId = (String) request.getSession().getAttribute(
                Constants.KAPTCHA_SESSION_KEY);
        LOG.debug("expect captcha : "+captchaId);
        

        if (!captchaId.equalsIgnoreCase(captcha)) {
            LOG.debug("captcha is wrong");
            addFieldError(fieldName, object);
        }
        
        
        
    }
    
    

}
