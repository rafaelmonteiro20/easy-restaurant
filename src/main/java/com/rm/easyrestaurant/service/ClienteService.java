package com.rm.easyrestaurant.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rm.easyrestaurant.model.Cliente;
import com.rm.easyrestaurant.repository.Clientes;
import com.rm.easyrestaurant.service.exception.DocumentoExistenteException;

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