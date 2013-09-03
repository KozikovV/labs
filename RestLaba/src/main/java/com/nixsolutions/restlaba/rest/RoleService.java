package com.nixsolutions.restlaba.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.nixsolutions.restlaba.domain.Role;

@Path(RoleService.ROLE_PATH)
public interface RoleService {
	
	public static final String ROLE_PATH = "/role";

	@GET
	@Path("/byId/{id}")
	public Role getRoleById(@PathParam("id") Long id);
	
	@GET
	@Path("/all")
	public List<Role> getRoles();
	
	@GET
	@Path("/byName/{name}")
	public Role getRoleByName(@PathParam("name") String name);
	
}
