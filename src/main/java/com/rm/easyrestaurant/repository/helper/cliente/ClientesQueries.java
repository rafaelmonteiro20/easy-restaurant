package com.rm.easyrestaurant.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rm.easyrestaurant.model.Cliente;
import com.rm.easyrestaurant.repository.filter.ClienteFilter;

public interface ClientesQueries {

	Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);
	
}