package com.easyrestaurant.repository.filter;

import java.util.List;

import com.easyrestaurant.model.Grupo;

public class UsuarioFilter {

	private String nome;

	private List<Grupo> grupos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	public boolean hasGrupoSelecionado() {
		return grupos != null && !grupos.isEmpty();
	}
	
}