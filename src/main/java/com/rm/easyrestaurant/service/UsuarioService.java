package com.rm.easyrestaurant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.rm.easyrestaurant.model.Usuario;
import com.rm.easyrestaurant.repository.Usuarios;
import com.rm.easyrestaurant.service.exception.EmailUsuarioJaCadastradoException;
import com.rm.easyrestaurant.service.exception.SenhaObrigatoriaUsuarioException;

@Service
public class UsuarioService {
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
		
	@Transactional
	public void save(Usuario usuario) {
		Optional<Usuario> existente = usuarios.findByEmail(usuario.getEmail());
		
		if (existente.isPresent())
			throw new EmailUsuarioJaCadastradoException("E-mail já cadastrado");
		
		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha()))
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para novo usuário");
		
		if (usuario.isNovo()) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmacaoSenha(usuario.getSenha());
		}
		
		usuarios.save(usuario);
	}
	
}