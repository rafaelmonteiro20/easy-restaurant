package com.rm.easyrestaurant.model;

import com.rm.easyrestaurant.model.validation.group.CNPJGroup;
import com.rm.easyrestaurant.model.validation.group.CPFGroup;

public enum TipoPessoa {

	FISICA("Física", "CPF", "000.000.000-00", CPFGroup.class), 
	JURIDICA("Jurídica", "CNPJ", "00.000.000/0000-00", CNPJGroup.class);

	private String descricao;
	private String documento;
	private String mascara;
	private Class<?> grupo;

	TipoPessoa(String descricao, String documento, String mascara, Class<?> grupo) {
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.grupo = grupo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getMascara() {
		return mascara;
	}
	
	public Class<?> getGrupo() {
		return grupo;
	}
	
}