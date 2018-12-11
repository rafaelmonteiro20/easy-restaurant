package com.easyrestaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyrestaurant.model.Product;
import com.easyrestaurant.repository.Products;
import com.easyrestaurant.service.event.ProdutoSalvoEvent;

@Service
public class ProdutoService {

	@Autowired
	private Products produtos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void save(Product produto) {
		produtos.save(produto);
		publisher.publishEvent(new ProdutoSalvoEvent(produto));
	}
	
}