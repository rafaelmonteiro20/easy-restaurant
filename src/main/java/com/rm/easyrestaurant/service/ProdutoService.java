package com.rm.easyrestaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rm.easyrestaurant.model.Produto;
import com.rm.easyrestaurant.repository.Produtos;
import com.rm.easyrestaurant.service.event.ProdutoSalvoEvent;

@Service
public class ProdutoService {

	@Autowired
	private Produtos produtos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void save(Produto produto) {
		produtos.save(produto);
		publisher.publishEvent(new ProdutoSalvoEvent(produto));
	}
	
}