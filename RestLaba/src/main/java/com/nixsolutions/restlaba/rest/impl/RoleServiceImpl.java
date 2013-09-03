package com.nixsolutions.restlaba.rest.impl;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.nixsolutions.restlaba.Constants;
import com.nixsolutions.restlaba.dao.RoleDao;
import com.nixsolutions.restlaba.domain.Role;
import com.nixsolutions.restlaba.rest.RoleService;

@Path(RoleService.ROLE_PATH)
@Produces(Constants.DATA_TYPE)
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	@GET
	@Path("/byId/{id}")
	public Role getRoleById(@PathParam("id") Long id) {
		Role role = roleDao.findById(id);
		return role;
	}

	@Override
	@GET
	@Path("/all")
	public List<Role> getRoles() {
		List<Role> roles = roleDao.findAll();
		return roles;
	}

	@Override
	@GET
	@Path("/byName/{name}")
	public Role getRoleByName(@PathParam("name") String name) {
		Role role = roleDao.findByName(name);
		return role;
	}

}
