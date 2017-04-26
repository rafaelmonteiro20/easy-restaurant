package com.rm.easyrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rm.easyrestaurant.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long> {

}