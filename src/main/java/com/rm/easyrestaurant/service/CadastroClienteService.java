package com.rm.easyrestaurant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rm.easyrestaurant.exception.DocumentoExistenteException;
import com.rm.easyrestaurant.model.Cliente;
import com.rm.easyrestaurant.repository.Clientes;

@Service
public class CadastroClienteService {

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