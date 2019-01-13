package com.easyrestaurant.security;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.easyrestaurant.model.User;
import com.easyrestaurant.repository.Permissions;
import com.easyrestaurant.repository.Users;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private Users users;
	
	@Autowired
	private Permissions permissions;
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		Optional<User> userOptional = users.findByMailAndActiveTrue(mail);
		User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("E-mail inválido."));
		return new org.springframework.security.core.userdetails.User(
				user.getMail(), user.getPassword(), loadAuthorities(user));
	}
	
	private Set<? extends GrantedAuthority> loadAuthorities(User user) {
		
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		loadPermissions(user).forEach(p -> {
			authorities.add(new SimpleGrantedAuthority(p.toUpperCase()));
		});
		
		return authorities;
	}
	
	private List<String> loadPermissions(User user) {
		if(user.isRoot()) {
			return permissions.findAllRoles();
		}
		
		return permissions.findByUser(user);
	}

}
