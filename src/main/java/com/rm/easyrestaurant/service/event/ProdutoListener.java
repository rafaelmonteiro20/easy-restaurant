package com.rm.easyrestaurant.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rm.easyrestaurant.storage.FotoStorage;

@Component
public class ProdutoListener {

	@Autowired
	private FotoStorage fotoStorage;
	
	@EventListener(condition = "#evento.hasFoto()")
	public void produtoSalvo(ProdutoSalvoEvent evento) {
		fotoStorage.salvar(evento.getProduto().getFoto());
	}
	
}