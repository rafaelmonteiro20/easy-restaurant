package com.rm.easyrestaurant.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.rm.easyrestaurant.validation.SKU;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@SKU
	@NotBlank(message = "SKU é obrigatório")
	@Column(length = 8, nullable = false, unique = true)
	private String sku;

	@Length(max = 100, message = "O tamanho do nome deve ser no máximo 100 caracteres")
	@NotBlank(message = "Nome é obrigatório")
	@Column(length = 100, nullable = false)
	private String nome;

	private String descricao;

	@DecimalMin(value = "0.50", message = "O valor do produto deve ser no mínimo R$ 0,50")
	@DecimalMax(value = "999.99", message = "O valor do produto deve ser no máximo R$ 999,99")
	@NotNull(message = "Valor unitário é obrigatório")
	@Column(name = "valor_unitario", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorUnitario;

	@Max(value = 1000, message = "Quantidade em estoque deve ser no máximo 1000")
	@NotNull(message = "Quantidade estoque é obrigatório")
	@Column(name = "quantidade_estoque", nullable = false)
	private Integer quantidadeEstoque;

	@NotNull(message = "Categoria é obrigatório")
	@ManyToOne
	@JoinColumn(name = "codigo_categoria", nullable = false)
	private Categoria categoria;
	
	private boolean ativo = true;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

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

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}