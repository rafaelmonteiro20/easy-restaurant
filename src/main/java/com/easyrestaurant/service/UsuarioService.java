package com.easyrestaurant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.easyrestaurant.model.User;
import com.easyrestaurant.repository.Users;
import com.easyrestaurant.service.exception.ExistingRecordException;

@Service
public class UsuarioService {
	
	@Autowired
	private Users users;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
		
	@Transactional
	public void save(User user) {
		Optional<User> existing = users.findByMail(user.getMail());
		
		if (existing.isPresent()) {
			throw new ExistingRecordException("E-mail já cadastrado");
		}
		
		if (user.isNew() && StringUtils.isEmpty(user.getPassword())) {
			throw new IllegalArgumentException("Senha é obrigatória para novo usuário");
		}
		
//		if (usuario.isNovo()) {
//			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
//			usuario.setConfirmacaoSenha(usuario.getSenha());
//		}
		
		users.save(user);
	}
	
	@Transactional
	public void alterarStatus(Long[] codigos, StatusUsuario status) {
		status.alterar(codigos, users);
	}
	
}
