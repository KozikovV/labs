package com.nixsolutions.domain.assembler;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nixsolutions.domain.User;
import com.nixsolutions.soaplaba.soap.types.UserType;

public class UserAssembler {
	
	private static final Log LOG = LogFactory.getLog(UserAssembler.class);

	public static UserType toUserType(User user) {
		
		if (user == null) {
			return null;
		}
		
		UserType userType = new UserType();
		userType.setId(user.getId());
		userType.setFirstName(user.getFirstName());
		userType.setLastName(user.getLastName());
		userType.setLogin(user.getLogin());
		userType.setEmail(user.getEmail());
		userType.setPassword(user.getPassword());
		userType.setBirthDate(toXMLGregorianCalendar(user.getBirthDate()));
		userType.setRole(RoleAssembler.toRoleType(user.getRole()));
		return userType;
	}

	private static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);

		XMLGregorianCalendar xmlGregorianCalendar = null;
		try {
			xmlGregorianCalendar = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(cal);
		} catch (DatatypeConfigurationException e) {
			LOG.error("Was error while was converting from Date to XMLGregorianCalendar", e);
			e.printStackTrace();
		}

		return xmlGregorianCalendar;
	}

	public static User toUser(UserType userType) {
		
		if (userType == null) {
			return null;
		}
		
		User user = new User();
		user.setId(userType.getId());
		user.setFirstName(userType.getFirstName());
		user.setLastName(userType.getLastName());
		user.setLogin(userType.getLogin());
		user.setEmail(userType.getEmail());
		user.setPassword(userType.getPassword());
		user.setBirthDate(toDate(userType.getBirthDate()));
		user.setRole(RoleAssembler.toRole(userType.getRole()));
		return user;
	}
	
	private static Date toDate(XMLGregorianCalendar xmlGregorianCalendar){
		return xmlGregorianCalendar.toGregorianCalendar().getTime();
	}

}
