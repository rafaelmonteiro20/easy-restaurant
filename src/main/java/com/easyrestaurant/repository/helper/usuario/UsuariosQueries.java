package com.easyrestaurant.repository.helper.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.easyrestaurant.model.Usuario;
import com.easyrestaurant.repository.filter.UsuarioFilter;

public interface UsuariosQueries {

	public Page<Usuario> pesquisar(UsuarioFilter filtro, Pageable pageable);
	
}