package com.easyrestaurant.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.easyrestaurant.validation.ConfirmacaoSenha;

//@ConfirmacaoSenha(atributo = "senha", atributoConfirmacao = "confirmacaoSenha")
//@Entity
//@DynamicUpdate
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank
	private String nome;

	@NotBlank
	@Email
	private String email;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	private String senha;

	@Transient
	private String confirmacaoSenha;

	private Boolean ativo = true;

//	@NotNull
//	@ManyToMany
//	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "codigo_usuario"),
//				inverseJoinColumns = @JoinColumn(name = "codigo_grupo"))	
//	private List<Grupo> grupos;

	public Usuario(Long codigo) {
		this.codigo = codigo;
	}
	
	public Usuario() {

	}

	@PreUpdate
	private void preUpdate() {
		this.confirmacaoSenha = senha;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}
	
	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public Boolean getAtivo() {
		return ativo;
	}
	
	public void ativar() {
		this.ativo = true;
	}
	
	public void desativar() {
		this.ativo = false;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

//	public List<Grupo> getGrupos() {
//		return grupos;
//	}
//
//	public void setGrupos(List<Grupo> grupos) {
//		this.grupos = grupos;
//	}
//	
//	public String getNomesGrupos() {
//		if(grupos == null)
//			return "";
//		
//		return grupos.stream().map(Grupo::getNome).collect(Collectors.joining(", "));
//	}
	
	public boolean isNovo() {
		return codigo == null;
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
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}