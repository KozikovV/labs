package com.nixsolutions.web.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.logging.LogFactory;
import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;

/**
 * Bean of user.
 * 
 * @author zinchenko
 * 
 */
public class UserBean {

    protected Long id;

    protected String firstName;

    protected String lastName;

    protected String login;

    protected String email;

    protected String password;
    
    private String passwordAgain;

    protected Date birthDate;

    protected Role role;

    protected String captcha;

    public static UserBean UserToUserBean(User user) {
        UserBean userBean = new UserBean(user.getId(), user.getFirstName(),
                user.getLastName(), user.getLogin(), user.getEmail(),
                user.getPassword(), user.getBirthDate(), user.getRole());
        return userBean;
    }

    public static User UserBeanToUser(UserBean userBean) {
        User user = new User(userBean.getId(), userBean.getFirstName(),
                userBean.getLastName(), userBean.getLogin(),
                userBean.getEmail(), userBean.getPassword(),
                userBean.getBirthDate(), userBean.getRole());
        return user;
    }

    public UserBean() {

    }

    public UserBean(final Long id, final String firstName,
            final String lastName, final String login, final String email,
            final String password, final Date birthDate, final Role role) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @RequiredStringValidator(key="error.required")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    @RequiredStringValidator(key="error.required")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {

    	if (birthDate == null) {
			return -1;
		}
    	
        Calendar birthDateCalendar = Calendar.getInstance();
        birthDateCalendar.setTime(birthDate);
        int birthYear = birthDateCalendar.get(Calendar.YEAR);

        Date todayDate = new Date();
        Calendar todayDateCalendar = Calendar.getInstance();
        todayDateCalendar.setTime(todayDate);
        int todayYear = todayDateCalendar.get(Calendar.YEAR);

        int age = todayYear - birthYear;

        return age;
    }

    @RequiredStringValidator(key="error.required")
    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    @RequiredStringValidator(key="error.required")
    @EmailValidator(key="error.email")
    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @RequiredStringValidator(key="error.required")
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    @RequiredFieldValidator(key="error.required")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getBirthDateFormat() {
        if (birthDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateFormat = sdf.format(birthDate);
            return dateFormat;
        } else {
            return "";
        }

    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
    	try {
			return BeanUtils.describe(this).remove("age").toString();
		} catch (Exception e) {
			LogFactory.getLog(UserBean.class).error(
					"could not correct invoke a toString method", e);
		}
		return super.toString();
    }

    @Override
    public int hashCode() {
        
        return HashCodeBuilder.reflectionHashCode(this);
        
    }

    @Override
    public boolean equals(final Object obj) {
       
        return EqualsBuilder.reflectionEquals(this, obj);
        
    }

}
