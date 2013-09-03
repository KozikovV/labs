package com.nixsolutions.soaplaba.domain.assembler;

import com.nixsolutions.soaplaba.domain.Role;
import com.nixsolutions.soaplaba.soap.types.RoleType;

public class RoleAssembler {
	
	public static RoleType toRoleType(Role role){
		
		if (role == null) {
			return null;
		}
		
		RoleType roleType = new RoleType();
		roleType.setId(role.getId());
		roleType.setName(role.getName());
		return roleType;
	}
	
	public static Role toRole(RoleType roleType){
		
		if (roleType == null) {
			return null;
		}
		
		Role role = new Role();
		role.setId(roleType.getId());
		role.setName(roleType.getName());
		return role;
	}

}
