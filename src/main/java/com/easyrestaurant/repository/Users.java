package com.easyrestaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrestaurant.model.User;
import com.easyrestaurant.repository.query.UsersQueries;

public interface Users extends JpaRepository<User, Long>, UsersQueries {

	Optional<User> findByMail(String mail);
	
	Optional<User> findByMailAndActiveTrue(String mail);
	
	List<User> findByIdIn(Long[] ids);
	
}
