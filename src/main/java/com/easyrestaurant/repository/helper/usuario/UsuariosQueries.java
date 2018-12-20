package com.easyrestaurant.repository.helper.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.easyrestaurant.model.Usuario;
import com.easyrestaurant.repository.filter.UserFilter;

public interface UsuariosQueries {

	public Page<Usuario> pesquisar(UserFilter filtro, Pageable pageable);
	
}