package com.easyrestaurant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyrestaurant.model.Categoria;
import com.easyrestaurant.repository.Categorias;
import com.easyrestaurant.service.exception.CategoriaExistenteException;

@Service
public class CategoriaService {

	@Autowired
	private Categorias categorias;
	
	@Transactional
	public Categoria save(Categoria categoria) {
		Optional<Categoria> optional = categorias.findByNomeIgnoreCase(categoria.getNome());
		
		if(optional.isPresent())
			throw new CategoriaExistenteException("Categoria já cadastrada no sistema.");
		
		return categorias.saveAndFlush(categoria);
	}
	
}