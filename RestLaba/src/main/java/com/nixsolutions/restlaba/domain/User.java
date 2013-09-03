package com.nixsolutions.restlaba.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.GenericGenerator;

/**
 * User.
 * 
 * @author zinchenko
 * 
 */
@XmlRootElement(name="user")
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    protected Long id;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;

    @Column(name = "login")
    protected String login;

    @Column(name = "email")
    protected String email;

    @Column(name = "password")
    protected String password;

    @Column(name = "birth_date")
    protected Date birthDate;

    @ManyToOne
    @JoinColumn(name = "role_id")
    protected Role role;

    public User() {

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
			return BeanUtils.describe(this).toString();
		} catch (Exception e) {
			LogFactory.getLog(User.class).error("could not correct invoke a toString method", e);
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
