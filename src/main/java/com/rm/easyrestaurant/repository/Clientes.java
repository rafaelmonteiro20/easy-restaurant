package com.rm.easyrestaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rm.easyrestaurant.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByDocumento(String documento);
	
}