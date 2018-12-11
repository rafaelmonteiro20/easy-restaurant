package com.easyrestaurant.buider;

import java.math.BigDecimal;

import com.easyrestaurant.model.Product;

public class ProdutoBuilder {

	private Product produto = new Product();
	
	public ProdutoBuilder comCodigo(Long codigo) {
		produto.setCodigo(codigo);
		return this;
	}
	
	public ProdutoBuilder comValorUnitarioDe(String valor) {
		produto.setValorUnitario(new BigDecimal(valor));
		return this;
	}
	
	public Product build() {
		return produto;
	}
	
}
