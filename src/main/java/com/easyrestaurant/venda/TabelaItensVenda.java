package com.easyrestaurant.venda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.easyrestaurant.model.ItemVenda;
import com.easyrestaurant.model.Produto;

public class TabelaItensVenda {

	private List<ItemVenda> itens = new ArrayList<>();
	
	public void adicionarItem(Produto produto, Integer quantidade) {
		ItemVenda item = new ItemVenda();
		item.setProduto(produto);
		item.setQuantidade(quantidade);
		item.setValorUnitario(produto.getValorUnitario());
		
		itens.add(item);
	}
	
	public BigDecimal getValorTotal() {
		return itens.stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
