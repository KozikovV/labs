package com.nixsolutions.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.api.UserApi;
import com.nixsolutions.domain.User;
import com.nixsolutions.domain.assembler.UserAssembler;
import com.nixsolutions.soaplaba.soap.PortType;
import com.nixsolutions.soaplaba.soap.types.UserType;

@Service
public class UserApiImpl implements UserApi {

	@Autowired
	private PortType portType = null;
	
	@Override
	public List<User> findAll() {
		
		Map<String, Object> map = ((BindingProvider)portType).getRequestContext();
		map.put("ws-security.username", "libuser");
		map.put("ws-security.password", "books");
		
		
		
		List<User> users = new ArrayList<User>();
		List<UserType> userTypes = portType.getUsers();
		for (UserType userType : userTypes) {
			users.add(UserAssembler.toUser(userType));
		}
		
		return users;
	}

	@Override
	public void create(User user) {
		portType.addUser(UserAssembler.toUserType(user));
	}

	@Override
	public void update(User user) {
		portType.updateUser(UserAssembler.toUserType(user));
	}

	@Override
	public User findByLogin(String login) {
		UserType userType = portType.getUserByLogin(login);
		return UserAssembler.toUser(userType);
	}

	@Override
	public User findByEmail(String login) {
		
		UserType userType = portType.getUserByLogin(login);
		return UserAssembler.toUser(userType);
	}

	@Override
	public void remove(Long id) {
		portType.removeUser(id);
	}
	
	public void setPortType(PortType portType) {
		this.portType = portType;
	}

}