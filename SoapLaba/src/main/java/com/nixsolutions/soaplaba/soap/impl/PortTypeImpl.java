package com.nixsolutions.soaplaba.soap.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.nixsolutions.soaplaba.dao.RoleDao;
import com.nixsolutions.soaplaba.dao.UserDao;
import com.nixsolutions.soaplaba.domain.Role;
import com.nixsolutions.soaplaba.domain.User;
import com.nixsolutions.soaplaba.domain.assembler.RoleAssembler;
import com.nixsolutions.soaplaba.domain.assembler.UserAssembler;
import com.nixsolutions.soaplaba.soap.PortType;
import com.nixsolutions.soaplaba.soap.types.RoleType;
import com.nixsolutions.soaplaba.soap.types.UserType;

public class PortTypeImpl implements PortType {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@WebResult(name = "responseType", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types")
	@RequestWrapper(localName = "getUsersNumber", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.GetUsersNumber")
	@WebMethod(action = "http://soaplaba.nixsolutions.com/getUsersNumber")
	@ResponseWrapper(localName = "getUsersNumberResponse", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.GetUsersNumberResponse")
	public Integer getUsersNumber() {
		return userDao.findAll().size();
	}



	@WebResult(name = "responseType", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types")
	@RequestWrapper(localName = "getUserById", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.GetUserById")
	@WebMethod
	@ResponseWrapper(localName = "getUserByIdResponse", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.GetUserByIdResponse")
	public com.nixsolutions.soaplaba.soap.types.UserType getUserByLogin(
			@WebParam(name = "requestType", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types") String login) {

		User user = userDao.findByLogin(login);

		UserType userOut = UserAssembler.toUserType(user);

		return userOut;
	}

	@WebResult(name = "return", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types")
	@RequestWrapper(localName = "getUsers", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.GetUsers")
	@WebMethod
	@ResponseWrapper(localName = "getUsersResponse", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.GetUsersResponse")
	public List<UserType> getUsers() {

		List<UserType> userTypes = new ArrayList<UserType>();
		List<User> users = userDao.findAll();
		for (User user : users) {
			userTypes.add(UserAssembler.toUserType(user));
		}

		return userTypes;
	}

	@RequestWrapper(localName = "addUser", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.AddUser")
	@WebMethod
	@ResponseWrapper(localName = "addUserResponse", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.AddUserResponse")
	public void addUser(
			@WebParam(name = "user", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types") UserType userType) {

		User user = UserAssembler.toUser(userType);
		userDao.create(user);

	}

	@RequestWrapper(localName = "updateUser", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.UpdateUser")
	@WebMethod
	@ResponseWrapper(localName = "updateUserResponse", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.UpdateUserResponse")
	public void updateUser(
			@WebParam(name = "userType", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types") UserType userType) {
		
		User user = UserAssembler.toUser(userType);
		userDao.update(user);
		
	}

	@WebResult(name = "return", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types")
	@RequestWrapper(localName = "getRoles", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.GetRoles")
	@WebMethod
	@ResponseWrapper(localName = "getRolesRosponse", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.GetRolesRosponse")
	public List<RoleType> getRoles() {
		
		List<RoleType> roleTypes = new ArrayList<RoleType>();
		List<Role> roles = roleDao.findAll();
		for (Role role : roles) {
			roleTypes.add(RoleAssembler.toRoleType(role));
		}
		
		return roleTypes;
	}

	@RequestWrapper(localName = "removeUser", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.RemoveUser")
	@WebMethod
	@ResponseWrapper(localName = "removeUserResponse", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.RemoveUserResponse")
	public void removeUser(
			@WebParam(name = "id", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types") Long id) {

		userDao.remove(id);
		
	}

	@WebResult(name = "return", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types")
	@RequestWrapper(localName = "findByEmailUser", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.FindByEmailUser")
	@WebMethod
	@ResponseWrapper(localName = "findByEmailUserResponse", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types", className = "com.nixsolutions.soaplaba.soap.types.FindByEmailUserResponse")
	public UserType findByEmailUser(
			@WebParam(name = "email", targetNamespace = "http://soaplaba.nixsolutions.com/soap/types") String email) {
		
		User user = userDao.findByEmail(email);
		
		return UserAssembler.toUserType(user);
	}


}
