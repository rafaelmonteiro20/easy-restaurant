package com.easyrestaurant.buider;

import java.math.BigDecimal;

import com.easyrestaurant.model.Produto;

public class ProdutoBuilder {

	private Produto produto = new Produto();
	
	public ProdutoBuilder comCodigo(Long codigo) {
		produto.setCodigo(codigo);
		return this;
	}
	
	public ProdutoBuilder comValorUnitarioDe(String valor) {
		produto.setValorUnitario(new BigDecimal(valor));
		return this;
	}
	
	public Produto build() {
		return produto;
	}
	
}
