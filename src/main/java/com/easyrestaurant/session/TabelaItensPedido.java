package com.easyrestaurant.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.easyrestaurant.model.ItemPedido;
import com.easyrestaurant.model.Produto;

@Component
@SessionScope
public class TabelaItensPedido {

	private List<ItemPedido> itens = new ArrayList<>();
	
	public void adicionarItem(Produto produto, Integer quantidade) {
		Optional<ItemPedido> itemVendaOptional = buscarItemExistente(produto);
		
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

	private Optional<ItemPedido> buscarItemExistente(Produto produto) {
		return itens.stream().filter(i -> i.getProduto().equals(produto)).findAny();
	}
	
	public BigDecimal getValorTotal() {
		return itens.stream()
				.map(ItemPedido::getValorTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	public int quantidadeDeItens() {
		return itens.size();
	}

	public List<ItemPedido> getItens() {
		return Collections.unmodifiableList(itens);
	}

}
