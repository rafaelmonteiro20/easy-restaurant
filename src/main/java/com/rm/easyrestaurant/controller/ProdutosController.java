package com.rm.easyrestaurant.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rm.easyrestaurant.controller.page.PageWrapper;
import com.rm.easyrestaurant.model.Produto;
import com.rm.easyrestaurant.repository.Categorias;
import com.rm.easyrestaurant.repository.Produtos;
import com.rm.easyrestaurant.repository.filter.ProdutoFilter;
import com.rm.easyrestaurant.service.CadastroProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private Categorias categorias;
	
	@Autowired
	private CadastroProdutoService service;
	
	@Autowired
	private Produtos produtos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView mv = new ModelAndView("produtos/CadastroProduto");
		mv.addObject("categorias", categorias.findAll());
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Produto produto, BindingResult result, 
			Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors())
			return novo(produto);
		
		service.save(produto);
		
		attributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso.");
		return new ModelAndView("redirect:/produtos/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ProdutoFilter produtoFilter, BindingResult result, 
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("produtos/PesquisaProdutos");
		mv.addObject("categorias", categorias.findAll());
		
		PageWrapper<Produto> pageWrapper = 
				new PageWrapper<>(produtos.filtrar(produtoFilter, pageable), request);
		
		mv.addObject("pagina", pageWrapper);
		return mv;
	}
	
}