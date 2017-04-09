package com.rm.easyrestaurant.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rm.easyrestaurant.model.Produto;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@RequestMapping("/novo")
	public String novo(Produto produto) {
		return "produtos/CadastroProduto";
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Produto produto, BindingResult result, 
			Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors())
			return novo(produto);
		
		attributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso.");
		return "redirect:/produtos/novo";
	}
	
}