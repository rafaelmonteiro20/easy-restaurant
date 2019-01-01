package com.easyrestaurant.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easyrestaurant.model.Pedido;
import com.easyrestaurant.model.Product;
import com.easyrestaurant.model.User;
import com.easyrestaurant.repository.Products;
import com.easyrestaurant.service.PedidoService;
import com.easyrestaurant.session.TabelasItensSession;

//@Controller
//@RequestMapping("/pedidos")
public class PedidosController {

	@Autowired
	private Products produtos;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private TabelasItensSession tabelas;
	
	@GetMapping("/form")
	public ModelAndView form(Pedido pedido) {
		ModelAndView mv = new ModelAndView("pedidos/CadastroPedido");
		pedido.setUuid(UUID.randomUUID().toString());
		return mv;
	}
	
	@PostMapping("/form")
	public ModelAndView salvar(Pedido pedido, RedirectAttributes attributes) {
		pedido.setUsuario(new User(1L));
		pedido.adicionarItens(tabelas.getItens(pedido.getUuid()));
		
		pedidoService.salvar(pedido);
		attributes.addFlashAttribute("mensagem", "Pedido salvo com sucesso.");
		return new ModelAndView("redirect:/pedidos/form");
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoProduto, String uuid) {
		Product produto = produtos.findOne(codigoProduto);
		tabelas.adicionarItem(uuid, produto, 1);
		return mvTabelaItensPedido(uuid);
	}
	
	@PutMapping("/item/{codigoProduto}")
	public ModelAndView alterarQuantidadeDoItem(@PathVariable("codigoProduto") Product produto, 
			Integer quantidade, String uuid) {
		
		tabelas.alterarQuantidade(uuid, produto, quantidade);
		return mvTabelaItensPedido(uuid);
	}
	
	@DeleteMapping("/item/{uuid}/{codigoProduto}")
	public ModelAndView removerItem(@PathVariable("codigoProduto") Product produto, 
			@PathVariable("uuid") String uuid) {
		
		tabelas.removerItem(uuid, produto);
		return mvTabelaItensPedido(uuid);
	}

	private ModelAndView mvTabelaItensPedido(String uuid) {
		ModelAndView mv = new ModelAndView("pedidos/TabelaItensVenda");
		mv.addObject("itens", tabelas.getItens(uuid));
		mv.addObject("valorTotal", tabelas.getValorTotal(uuid));
		return mv;
	}
	
}