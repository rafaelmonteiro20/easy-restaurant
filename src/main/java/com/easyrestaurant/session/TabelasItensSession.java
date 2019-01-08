package com.easyrestaurant.session;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.easyrestaurant.model.ItemOrder;
import com.easyrestaurant.model.Product;

@Component
@SessionScope
public class TabelasItensSession {

	private Set<TabelaItensPedido> tabelas = new HashSet<>();

	public void adicionarItem(String uuid, Product produto, int quantidade) {
		TabelaItensPedido tabela = buscarTabela(uuid);
		tabela.adicionarItem(produto, quantidade);
		tabelas.add(tabela);
	}
	
	public void alterarQuantidade(String uuid, Product produto, Integer quantidade) {
		TabelaItensPedido tabela = buscarTabela(uuid);
		tabela.alterarQuantidade(produto, quantidade);
	}

	public void removerItem(String uuid, Product produto) {
		TabelaItensPedido tabela = buscarTabela(uuid);
		tabela.removerItem(produto);
	}

	public List<ItemOrder> getItens(String uuid) {
		return buscarTabela(uuid).getItens();
	}
	
	public BigDecimal getValorTotal(String uuid) {
		return buscarTabela(uuid).getValorTotal();
	}
	
	private TabelaItensPedido buscarTabela(String uuid) {
		return tabelas.stream()
					  .filter(t -> t.getUuid().equals(uuid))
					  .findAny().orElse(new TabelaItensPedido(uuid));
	}
	
}
