package com.easyrestaurant.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.easyrestaurant.model.ItemPedido;
import com.easyrestaurant.model.Produto;

public class TabelaItensPedido {

	private List<ItemPedido> itens = new ArrayList<>();
	
	public void adicionarItem(Produto produto, Integer quantidade) {
		ItemPedido item = new ItemPedido();
		item.setProduto(produto);
		item.setQuantidade(quantidade);
		item.setValorUnitario(produto.getValorUnitario());
		
		itens.add(item);
	}
	
	public BigDecimal getValorTotal() {
		return itens.stream()
				.map(ItemPedido::getValorTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
