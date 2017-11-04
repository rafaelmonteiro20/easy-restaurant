package com.easyrestaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public @ResponseBody String adicionarItem(Long codigoProduto) {
		Produto produto = produtos.findOne(codigoProduto);
		tabelaItens.adicionarItem(produto, 1);
		
		System.out.println(tabelaItens.quantidadeDeItens());
		
		return "Item adicionado";
	}
	
}