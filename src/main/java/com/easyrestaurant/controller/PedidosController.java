package com.easyrestaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easyrestaurant.model.Produto;
import com.easyrestaurant.repository.Produtos;
import com.easyrestaurant.session.TabelaItensPedido;

@Controller
@RequestMapping("/pedidos")
public class PedidosController {

	@Autowired
	private Produtos produtos;
	
	@Autowired
	private TabelaItensPedido tabelaItens;
	
	@GetMapping("/form")
	public ModelAndView form() {
		return new ModelAndView("pedidos/CadastroPedido");
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoProduto) {
		Produto produto = produtos.findOne(codigoProduto);
		tabelaItens.adicionarItem(produto, 1);
		return mvTabelaItensPedido();
	}
	
	@PutMapping("/item/{codigoProduto}")
	public ModelAndView alterarQuantidadeDoItem(@PathVariable("codigoProduto") Produto produto, Integer quantidade) {
		tabelaItens.alterarQuantidade(produto, quantidade);
		return mvTabelaItensPedido();
	}
	
	@DeleteMapping("/item/{codigoProduto}")
	public ModelAndView removerItem(@PathVariable("codigoProduto") Produto produto) {
		tabelaItens.removerItem(produto);
		return mvTabelaItensPedido();
	}

	private ModelAndView mvTabelaItensPedido() {
		ModelAndView mv = new ModelAndView("pedidos/TabelaItensVenda");
		mv.addObject("itens", tabelaItens.getItens());
		return mv;
	}
	
}