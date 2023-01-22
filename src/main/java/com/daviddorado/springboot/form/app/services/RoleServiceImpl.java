package com.daviddorado.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.daviddorado.springboot.form.app.models.domain.Role;
@Service
public class RoleServiceImpl implements RoleService {
	private List<Role> roles;
	@Override
	public List<Role> listRoles() {
		this.roles =  Arrays.asList(new Role(1,"Administrador","ROLE_ADMIN"),new Role(2,"Usuario","ROLE_USER"),new Role(3,"Moderador","ROLE_MODERATOR"));
		return this.roles;
	}

	@Override
	public Role getById(Integer id) {
		for (Role role: this.roles) {
			if(role.getId() == id) return role;
		}
		return null;		
	}

}
