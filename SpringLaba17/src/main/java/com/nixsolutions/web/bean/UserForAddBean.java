package com.nixsolutions.web.bean;

import org.hibernate.validator.constraints.NotEmpty;

import com.nixsolutions.domain.User;

/**
 * Bean of user for add user. This bean with second password field.
 * 
 * @author zinchenko
 * 
 */
public class UserForAddBean extends UserBean {

    @NotEmpty
    private String passwordAgain;

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    @Override
    public String toString() {
        return "UserForAdd [passwordAgain=" + passwordAgain + ", id=" + id
                + ", firstName=" + firstName + ", lastName=" + lastName
                + ", login=" + login + ", email=" + email + ", password="
                + password + ", birthDate=" + birthDate + ", role=" + role
                + "]";
    }

}
