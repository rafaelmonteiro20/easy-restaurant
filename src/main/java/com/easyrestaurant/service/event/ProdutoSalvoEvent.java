package com.easyrestaurant.service.event;

import org.springframework.util.StringUtils;

import com.easyrestaurant.model.Product;

public class ProdutoSalvoEvent {

	private Product produto;

	public ProdutoSalvoEvent(Product produto) {
		this.produto = produto;
	}

	public Product getProduto() {
		return produto;
	}
	
	public boolean hasFoto() {
		return !StringUtils.isEmpty(produto.getFoto());
	}
	
}