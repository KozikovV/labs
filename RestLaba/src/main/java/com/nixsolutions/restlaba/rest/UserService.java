package com.nixsolutions.restlaba.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import com.nixsolutions.restlaba.Constants;
import com.nixsolutions.restlaba.domain.User;

@Path(UserService.USER_PATH)
public interface UserService {
	
	public static final String USER_PATH = "/user";

	@GET
	@Path("/byLogin/{login}")
	public User getUserByLogin(@PathParam(value = "login") String login);

	@GET
	@Path("/byEmail/{email}")
	public User getUserByEmail(@PathParam(value = "email") String email);

	@GET
	public List<User> getUsers();

	@DELETE
	@Path("/byLogin/{id}")
	public void deleteById(@PathParam("id") Long id);

	@POST
	@Consumes(Constants.DATA_TYPE)
	public void addUser(@Multipart(type = Constants.DATA_TYPE) User user);
	
	@DELETE
	@Consumes(Constants.DATA_TYPE)
	public void deleteUser(@Multipart(type = Constants.DATA_TYPE) User user);
	
	@PUT
	@Consumes(Constants.DATA_TYPE)
	public void update(@Multipart(type = Constants.DATA_TYPE) User user);
	
}
