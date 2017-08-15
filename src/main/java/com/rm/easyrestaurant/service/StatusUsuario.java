package com.rm.easyrestaurant.service;

import com.rm.easyrestaurant.model.Usuario;
import com.rm.easyrestaurant.repository.Usuarios;

public enum StatusUsuario {

	ATIVAR {
		@Override
		public void alterar(Long[] codigos, Usuarios usuarios) {
			usuarios.findByCodigoIn(codigos).forEach(Usuario::ativar);
		}
	},
	
	DESATIVAR {
		@Override
		public void alterar(Long[] codigos, Usuarios usuarios) {
			usuarios.findByCodigoIn(codigos).forEach(Usuario::desativar);
		}
	};
	
	public abstract void alterar(Long[] codigos, Usuarios usuarios);
	
}