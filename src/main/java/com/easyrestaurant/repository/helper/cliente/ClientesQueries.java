package com.easyrestaurant.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.easyrestaurant.model.Cliente;
import com.easyrestaurant.repository.filter.ClienteFilter;

public interface ClientesQueries {

	Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);
	
}