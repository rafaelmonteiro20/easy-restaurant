package com.easyrestaurant.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.easyrestaurant.model.ItemPedido;
import com.easyrestaurant.model.Produto;


class TabelaItensPedido {

	private String uuid;
	
	private List<ItemPedido> itens = new ArrayList<>();
	
	TabelaItensPedido(String uuid) {
		this.uuid = uuid;
	}
	
	public void adicionarItem(Produto produto, Integer quantidade) {
		Optional<ItemPedido> itemVendaOptional = buscarItemPor(produto);
		
		ItemPedido itemVenda = null;
		
		if (itemVendaOptional.isPresent()) {
			itemVenda = itemVendaOptional.get();
			itemVenda.addQuantidade(quantidade);
		} else {
			itemVenda = new ItemPedido();
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(quantidade);
			itemVenda.setValorUnitario(produto.getValorUnitario());
			itens.add(0, itemVenda);
		}
	}

	public void alterarQuantidade(Produto produto, Integer quantidade) {
		if(quantidade <= 0) 
			quantidade = 1;
		
		ItemPedido item = buscarItemPor(produto).get();
		item.setQuantidade(quantidade);
	}
	
	public void removerItem(Produto produto) {
		itens.removeIf(item -> item.getProduto().equals(produto));
	}
	
	public BigDecimal getValorTotal() {
		return itens.stream()
				.map(ItemPedido::getValorTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public int quantidadeDeItens() {
		return itens.size();
	}

	public List<ItemPedido> getItens() {
		return Collections.unmodifiableList(itens);
	}
	
	private Optional<ItemPedido> buscarItemPor(Produto produto) {
		return itens.stream().filter(i -> i.getProduto().equals(produto)).findAny();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		TabelaItensPedido other = (TabelaItensPedido) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

}
