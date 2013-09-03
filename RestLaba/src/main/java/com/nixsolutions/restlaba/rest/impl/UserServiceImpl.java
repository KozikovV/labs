package com.nixsolutions.restlaba.rest.impl;

import java.util.List;

import com.nixsolutions.restlaba.Constants;
import com.nixsolutions.restlaba.dao.UserDao;
import com.nixsolutions.restlaba.domain.User;
import com.nixsolutions.restlaba.rest.UserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.springframework.beans.factory.annotation.Autowired;

@Path(UserService.USER_PATH)
@Produces(Constants.DATA_TYPE)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@GET
	@Path("/byLogin/{login}")
	public User getUserByLogin(@PathParam(value = "login") String login) {
		User user = userDao.findByLogin(login);
		return user;
	}

	@Override
	@GET
	@Path("/byEmail/{email}")
	public User getUserByEmail(@PathParam(value = "email") String email) {
		User user = userDao.findByEmail(email);
		return user;
	}

	@Override
	@GET
	public List<User> getUsers() {
		List<User> users = userDao.findAll();
		return users;
	}

	@Override
	@DELETE
	@Path("/byLogin/{id}")
	public void deleteById(@PathParam("id") Long id) {
		userDao.remove(id);
		return;
	}

	@Override
	@POST
	@Consumes(Constants.DATA_TYPE)
	public void addUser(@Multipart(type = Constants.DATA_TYPE) User user) {
		userDao.create(user);
		return;
	}

	@Override
	@DELETE
	@Consumes(Constants.DATA_TYPE)
	public void deleteUser(@Multipart(type = Constants.DATA_TYPE) User user) {
		userDao.remove(user);
	}

	@Override
	@PUT
	@Consumes(Constants.DATA_TYPE)
	public void update(@Multipart(type = Constants.DATA_TYPE) User user) {
		userDao.update(user);
	}

}
