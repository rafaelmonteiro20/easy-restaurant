package com.rm.easyrestaurant.service.event;

import org.springframework.util.StringUtils;

import com.rm.easyrestaurant.model.Produto;

public class ProdutoSalvoEvent {

	private Produto produto;

	public ProdutoSalvoEvent(Produto produto) {
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}
	
	public boolean hasFoto() {
		return !StringUtils.isEmpty(produto.getFoto());
	}
	
}