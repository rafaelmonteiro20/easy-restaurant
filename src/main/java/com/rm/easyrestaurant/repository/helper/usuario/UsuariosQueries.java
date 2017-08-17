package com.rm.easyrestaurant.repository.helper.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rm.easyrestaurant.model.Usuario;
import com.rm.easyrestaurant.repository.filter.UsuarioFilter;

public interface UsuariosQueries {

	public Page<Usuario> pesquisar(UsuarioFilter filtro, Pageable pageable);
	
}