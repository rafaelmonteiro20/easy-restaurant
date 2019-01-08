package com.easyrestaurant.service;

import com.easyrestaurant.model.User;
import com.easyrestaurant.repository.Users;

public enum StatusUsuario {

	ATIVAR {
		@Override
		public void alterar(Long[] codigos, Users usuarios) {
//			usuarios.findByCodigoIn(codigos).forEach(User::ativar);
		}
	},
	
	DESATIVAR {
		@Override
		public void alterar(Long[] codigos, Users usuarios) {
//			usuarios.findByCodigoIn(codigos).forEach(User::desativar);
		}
	};
	
	public abstract void alterar(Long[] codigos, Users usuarios);
	
}