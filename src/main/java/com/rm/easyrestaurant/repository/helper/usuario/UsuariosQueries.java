package com.rm.easyrestaurant.repository.helper.usuario;

import java.util.List;

import com.rm.easyrestaurant.model.Usuario;
import com.rm.easyrestaurant.repository.filter.UsuarioFilter;

public interface UsuariosQueries {

	public List<Usuario> pesquisar(UsuarioFilter filtro);
	
}