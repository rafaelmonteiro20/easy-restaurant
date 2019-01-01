package com.easyrestaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrestaurant.model.User;

public interface Users extends JpaRepository<User, Long> {

	Optional<User> findByMail(String mail);

	List<User> findByIdIn(Long[] ids);
	
}
