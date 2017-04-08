package com.rm.easyrestaurant.model;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

public class Produto {

	@NotBlank
	private String sku;
	
	@NotBlank
	private String nome;
	
	private String descricao;
	
	private BigDecimal valor;
	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}