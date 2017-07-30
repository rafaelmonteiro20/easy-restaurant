package com.rm.easyrestaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rm.easyrestaurant.model.Cliente;
import com.rm.easyrestaurant.repository.helper.cliente.ClientesQueries;

public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries {

	Optional<Cliente> findByDocumento(String documento);

}