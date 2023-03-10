package com.daviddorado.springboot.form.app.models.domain;

public class Role {
	private Integer id;
	private String nombre;
	private String rol;

	public Role(Integer id, String nombre, String rol) {
		this.id = id;
		this.nombre = nombre;
		this.rol = rol;
	}

	public Role() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public boolean equals(Object obj) {
		try {
			if (this == obj) return true;
			if (!(obj instanceof Role)) return false;
			Role role = (Role) obj;
			return this.id != null && this.id.equals(role.getId());
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
