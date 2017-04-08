package com.rm.easyrestaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rm.easyrestaurant.model.Produto;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@RequestMapping("/novo")
	public String novo() {
		return "produtos/CadastroProduto";
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String cadastrar(Produto produto) {
		System.out.println("SKU: " + produto.getSku());
		
		
		return "produtos/CadastroProduto";
	}
	
}
