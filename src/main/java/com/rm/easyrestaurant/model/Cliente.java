package com.rm.easyrestaurant.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "cliente")
@GroupSequenceProvider(ClienteGroupSequenceProvider.class)
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Nome é obrigatório")
	@Column(nullable = false, length = 100)
	private String nome;

	@NotBlank(message = "Documento é obrigatório")
	@CPF(message = "CPF inválido", groups = CPFGroup.class)
	@CNPJ(message = "CNPJ inválido", groups = CNPJGroup.class)
	@Column(nullable = false, length = 25, unique = true)
	private String documento;

	@NotNull(message = "Tipo pessoa é obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 8)
	private TipoPessoa tipo;

	@Column(length = 20)
	private String telefone;

	@Email(message = "E-mail inválido")
	@Column(length = 80)
	private String email;

	@Embedded
	private Endereco endereco;

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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
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