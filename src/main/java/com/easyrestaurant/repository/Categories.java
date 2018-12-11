package com.easyrestaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyrestaurant.model.Category;

@Repository
public interface Categories extends JpaRepository<Category, Long> {

	Optional<Category> findByNameIgnoreCase(String name);
	
}
