package com.daviddorado.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.daviddorado.springboot.form.app.models.domain.Pais;
@Service
public class PaisServiceImpl implements PaisService {

	private List<Pais> lista;

	public PaisServiceImpl() {
		lista = Arrays.asList(new Pais(1, "ES", "España"), new Pais(2, "PT", "Portugal"), new Pais(3, "FR", "Francia"),
				new Pais(4, "AN", "Andorra"));
	}

	@Override
	public List<Pais> listar() {		
		return lista;
	}

	@Override
	public Pais getById(Integer id) {
		for (Pais pais: this.lista) {
			if(pais.getId() == id) return pais;
		}
		return null;
	}

	@Override
	public boolean addPais(Integer id, String codigo, String nombre) {		
		return false;
	}

	@Override
	public boolean borrarPais(Integer id) {		
		return false;
	}

}
