package com.nixsolutions.bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class CaptchaBean {
    
    public final static String SESSION_KEY_NAME = "mySessionKeyName";
    public final static String CORRECT = "Correct!";
    public final static String WRONG = "Wrong";    
    
    String status;
    String value;

    public String check() {

        // Compare the CAPTCHA value with the user entered value.
        String captchaValue = (String)((HttpSession) FacesContext
                .getCurrentInstance().getExternalContext().getSession(true))
                .getAttribute(SESSION_KEY_NAME);
        
        if(captchaValue.equalsIgnoreCase(value)) {
            status = CORRECT;
        } else {
            status = WRONG;
        }
        
        return "";
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSessionKeyName() {
        return SESSION_KEY_NAME;
    }

}
