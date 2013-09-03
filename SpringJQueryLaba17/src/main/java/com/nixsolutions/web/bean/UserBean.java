package com.nixsolutions.web.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;

/**
 * Bean of user.
 * 
 * @author zinchenko
 * 
 */
public class UserBean {

    protected Long id;

    @NotEmpty
    protected String firstName;

    @NotEmpty
    protected String lastName;

    @NotEmpty
    protected String login;

    @Email
    @NotEmpty
    protected String email;

    @NotEmpty
    protected String password;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date birthDate;

    @ManyToOne
    protected Role role;

    @NotEmpty
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {

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

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

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
        return "User [id=" + id + ", firstName=" + firstName + ", lastName="
                + lastName + ", login=" + login + ", email=" + email
                + ", password=" + password + ", birthDate=" + birthDate
                + ", role=" + role + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((birthDate == null) ? 0 : birthDate.hashCode());
        result = prime * result
                + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result
                + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserBean other = (UserBean) obj;
        if (birthDate == null) {
            if (other.birthDate != null) {
                return false;
            }
        } else if (!birthDate.equals(other.birthDate)) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        if (login == null) {
            if (other.login != null) {
                return false;
            }
        } else if (!login.equals(other.login)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (role == null) {
            if (other.role != null) {
                return false;
            }
        } else if (!role.equals(other.role)) {
            return false;
        }
        return true;
    }

}
