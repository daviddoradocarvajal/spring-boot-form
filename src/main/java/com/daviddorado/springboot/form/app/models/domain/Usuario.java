package com.daviddorado.springboot.form.app.models.domain;

import java.util.Date;

import com.daviddorado.springboot.form.app.validation.IdentificationRegex;
import com.daviddorado.springboot.form.app.validation.Requerido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Usuario {
	//@Pattern(regexp = "[0-9]{3}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}")
	@IdentificationRegex
	private String identificador;	
	//@NotEmpty
	private String nombre;
	@Requerido
	private String apellido;
	@NotBlank
	@Size(min = 3,max = 8)
	private String username;
	@Requerido
	private String password;	
	@Email
	@NotBlank
	private String email;	
	@NotNull
	@Min(5)
	@Max(20000)
	private Integer cuenta;	
	@NotNull
	@Past
	// IMPORTANTE -> "yyyy-MM-dd" Es el patrón con el que envia type date de html
	//@DateTimeFormat(pattern = "yyyy-MM-dd")		
	private Date fechaNac;
	@NotNull
	@Valid
	private Pais pais;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Integer getCuenta() {
		return cuenta;
	}

	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	
	

}
