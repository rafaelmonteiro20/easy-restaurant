package com.rm.easyrestaurant.repository.filter;

import com.rm.easyrestaurant.model.Categoria;

public class ProdutoFilter {

	private String sku;
	
	private String nome;
	
	private Categoria categoria;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}