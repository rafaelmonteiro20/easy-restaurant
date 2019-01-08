package com.easyrestaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrestaurant.model.Category;

public interface Categories extends JpaRepository<Category, Long> {

	Optional<Category> findByNameIgnoreCase(String name);
	
}
