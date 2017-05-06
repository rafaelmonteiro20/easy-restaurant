package com.rm.easyrestaurant.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.rm.easyrestaurant.model.Cliente;

public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<Cliente> {

	@Override
	public List<Class<?>> getValidationGroups(Cliente cliente) {
		List<Class<?>> grupos = new ArrayList<>();
		grupos.add(Cliente.class);
		
		if (isClienteSelecionado(cliente)) {
			grupos.add(cliente.getTipo().getGrupo());
		}
		
		return grupos;
	}

	private boolean isClienteSelecionado(Cliente cliente) {
		return cliente != null && cliente.getTipo() != null;
	}

}