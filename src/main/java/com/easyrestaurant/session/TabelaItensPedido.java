package com.easyrestaurant.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.easyrestaurant.model.ItemOrder;
import com.easyrestaurant.model.Product;


class TabelaItensPedido {

	private String uuid;
	
	private List<ItemOrder> itens = new ArrayList<>();
	
	TabelaItensPedido(String uuid) {
		this.uuid = uuid;
	}
	
	public void adicionarItem(Product produto, Integer quantidade) {
		Optional<ItemOrder> itemPedidoOptional = buscarItemPor(produto);
		
		ItemOrder item = null;
		
		if (itemPedidoOptional.isPresent()) {
			item = itemPedidoOptional.get();
			item.addQuantidade(quantidade);
		} else {
			item = new ItemOrder();
			item.setProduto(produto);
			item.setQuantidade(quantidade);
			item.setValorUnitario(produto.getValorUnitario());
			itens.add(0, item);
		}
	}

	public void alterarQuantidade(Product produto, Integer quantidade) {
		if(quantidade <= 0) 
			quantidade = 1;
		
		ItemOrder item = buscarItemPor(produto).get();
		item.setQuantidade(quantidade);
	}
	
	public void removerItem(Product produto) {
		itens.removeIf(item -> item.getProduto().equals(produto));
	}
	
	public BigDecimal getValorTotal() {
		return itens.stream()
				.map(ItemOrder::getValorTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public int quantidadeDeItens() {
		return itens.size();
	}

	public List<ItemOrder> getItens() {
		return Collections.unmodifiableList(itens);
	}
	
	private Optional<ItemOrder> buscarItemPor(Product produto) {
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
