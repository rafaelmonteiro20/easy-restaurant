package com.easyrestaurant.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.easyrestaurant.model.ItemPedido;
import com.easyrestaurant.model.Produto;

@Component
@SessionScope
public class TabelasItensSession {

	private Set<TabelaItensPedido> tabelas = new HashSet<>();

	public void adicionarItem(String uuid, Produto produto, int quantidade) {
		TabelaItensPedido tabela = buscarTabela(uuid);
		tabela.adicionarItem(produto, quantidade);
		tabelas.add(tabela);
	}
	
	public void alterarQuantidade(String uuid, Produto produto, Integer quantidade) {
		TabelaItensPedido tabela = buscarTabela(uuid);
		
		if(quantidade <= 0)
			quantidade = 1;
		
		tabela.alterarQuantidade(produto, quantidade);
	}

	public void removerItem(String uuid, Produto produto) {
		TabelaItensPedido tabela = buscarTabela(uuid);
		tabela.removerItem(produto);
	}

	public List<ItemPedido> getItens(String uuid) {
		return buscarTabela(uuid).getItens();
	}
	
	private TabelaItensPedido buscarTabela(String uuid) {
		return tabelas.stream()
					  .filter(t -> t.getUuid().equals(uuid))
					  .findAny().orElse(new TabelaItensPedido(uuid));
	}
	
}
