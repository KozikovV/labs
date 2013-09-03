package com.nixsolutions.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.api.RoleApi;
import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.assembler.RoleAssembler;
import com.nixsolutions.soaplaba.soap.PortType;
import com.nixsolutions.soaplaba.soap.types.RoleType;

@Service
public class RoleApiImpl implements RoleApi {
	
	@Autowired
	private PortType portType;

	@Override
	public List<Role> findAll() {
		
		List<Role> roles = new ArrayList<Role>();
		List<RoleType> roleTypes = portType.getRoles();
		for (RoleType roleType : roleTypes) {
			roles.add(RoleAssembler.toRole(roleType));
		}
		
		return roles;
	}
	
	public void setPortType(PortType portType) {
		this.portType = portType;
	}

}
