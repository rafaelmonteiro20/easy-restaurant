package com.rm.easyrestaurant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rm.easyrestaurant.model.Cliente;
import com.rm.easyrestaurant.model.Estado;
import com.rm.easyrestaurant.model.TipoPessoa;
import com.rm.easyrestaurant.repository.Clientes;
import com.rm.easyrestaurant.service.CadastroClienteService;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	private Clientes clientes;
	
	@Autowired
	private CadastroClienteService service;
	
	@GetMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView("clientes/CadastroCliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", Estado.values());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView cadastrar(@Valid Cliente cliente, BindingResult result,
			RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(cliente);
		}
		
		service.save(cliente);
		attributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso.");
		return new ModelAndView("redirect:/clientes/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("clientes/PesquisaClientes");
		mv.addObject("clientes", clientes.findAll());
		return mv;
	}
	
}