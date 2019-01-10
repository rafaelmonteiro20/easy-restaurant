package com.easyrestaurant.security;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.easyrestaurant.model.User;
import com.easyrestaurant.repository.Users;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private Users users;
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		Optional<User> userOptional = users.findByMailAndActiveTrue(mail);
		User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("E-mail inválido."));
		return new org.springframework.security.core.userdetails.User(
				user.getMail(), user.getPassword(), new HashSet<>());
	}

}
