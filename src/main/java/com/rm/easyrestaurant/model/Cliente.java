package com.rm.easyrestaurant.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.rm.easyrestaurant.model.validation.ClienteGroupSequenceProvider;
import com.rm.easyrestaurant.model.validation.group.CNPJGroup;
import com.rm.easyrestaurant.model.validation.group.CPFGroup;

@Entity
@GroupSequenceProvider(ClienteGroupSequenceProvider.class)
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank
	private String nome;

	@NotBlank
	@CPF(groups = CPFGroup.class)
	@CNPJ(groups = CNPJGroup.class)
	private String documento;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoPessoa tipo;

	private String telefone;

	@Email
	private String email;


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@PrePersist @PreUpdate
	public void preSave() {
		this.documento = TipoPessoa.removerSinais(this.documento);
	}
	
	@PostLoad
	public void postLoad() {
		this.documento = this.tipo.formatar(this.documento);
	}
	
	@Override
	public String toString() {
		return "[" + nome + ", " + tipo + ", " + documento + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Cliente))
			return false;
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}