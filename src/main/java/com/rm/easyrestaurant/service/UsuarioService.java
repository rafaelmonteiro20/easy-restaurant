package com.rm.easyrestaurant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rm.easyrestaurant.model.Usuario;
import com.rm.easyrestaurant.repository.Usuarios;
import com.rm.easyrestaurant.service.exception.EmailUsuarioJaCadastradoException;

@Service
public class UsuarioService {
	
	@Autowired
	private Usuarios usuarios;
		
	@Transactional
	public void save(Usuario usuario) {
		Optional<Usuario> existente = usuarios.findByEmail(usuario.getEmail());
		
		if (existente.isPresent())
			throw new EmailUsuarioJaCadastradoException("E-mail já cadastrado");
		
		usuarios.save(usuario);
	}
	
}