package com.rm.easyrestaurant.dto;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

public class ProdutoDTO {

	private Long codigo;

	private String sku;

	private String nome;

	private String categoria;

	private BigDecimal valorUnitario;
	
	private String foto;

	public ProdutoDTO(Long codigo, String sku, String nome, String categoria, BigDecimal valorUnitario, String foto) {
		this.codigo = codigo;
		this.sku = sku;
		this.nome = nome;
		this.valorUnitario = valorUnitario;
		this.categoria = categoria;
		this.foto = StringUtils.isEmpty(foto) ? "produto-mock.png" : foto;
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getSku() {
		return sku;
	}

	public String getNome() {
		return nome;
	}

	public String getCategoria() {
		return categoria;
	}
	
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public String getFoto() {
		return foto;
	}
	
}