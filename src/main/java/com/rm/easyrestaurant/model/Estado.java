package com.rm.easyrestaurant.model;

public enum Estado {

	CE("Ceará"),
	PE("Pernambuco"),
	PB("Paraíba"),
	RN("Rio Grande do Norte");
	
	private String descricao;

	private Estado(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}