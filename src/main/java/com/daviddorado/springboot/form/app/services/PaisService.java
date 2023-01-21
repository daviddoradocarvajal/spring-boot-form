package com.daviddorado.springboot.form.app.services;

import java.util.List;

import com.daviddorado.springboot.form.app.models.domain.Pais;

public interface PaisService {
	public List<Pais> listar();
	public Pais getById(Integer id);
	public boolean addPais(Integer id, String codigo, String nombre);
	public boolean borrarPais(Integer id);
}
