package com.easyrestaurant.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyrestaurant.model.Cliente;
import com.easyrestaurant.repository.Clientes;
import com.easyrestaurant.service.exception.DocumentoExistenteException;

@Service
public class ClienteService {

	@Autowired
	private Clientes clientes;
	
	@Transactional
	public void save(Cliente cliente) {
		Optional<Cliente> existente = clientes.findByDocumento(cliente.getDocumento());
		
		if(existente.isPresent())
			throw new DocumentoExistenteException("Documento já cadastrado.");
			
		clientes.save(cliente);
	}
	
}