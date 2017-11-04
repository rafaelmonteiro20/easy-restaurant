package com.easyrestaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrestaurant.model.Cliente;
import com.easyrestaurant.repository.helper.cliente.ClientesQueries;

public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries {

	Optional<Cliente> findByDocumento(String documento);

	List<Cliente> findByNomeStartingWithIgnoreCase(String nome);

}