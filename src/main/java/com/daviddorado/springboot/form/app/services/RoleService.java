package com.daviddorado.springboot.form.app.services;

import java.util.List;

import com.daviddorado.springboot.form.app.models.domain.Role;

public interface RoleService {
	public List<Role> listRoles();
	public Role getById(Integer id);
}
