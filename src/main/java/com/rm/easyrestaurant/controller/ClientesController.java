package com.rm.easyrestaurant.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rm.easyrestaurant.controller.page.PageWrapper;
import com.rm.easyrestaurant.model.Cliente;
import com.rm.easyrestaurant.model.TipoPessoa;
import com.rm.easyrestaurant.repository.Clientes;
import com.rm.easyrestaurant.repository.filter.ClienteFilter;
import com.rm.easyrestaurant.service.ClienteService;
import com.rm.easyrestaurant.service.exception.DocumentoExistenteException;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	private Clientes clientes;
	
	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping("/form")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView("clientes/CadastraCliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		return mv;
	}
	
	@PostMapping("/form")
	public ModelAndView cadastrar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors())
			return novo(cliente);
		
		try {
			clienteService.save(cliente);
			attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso.");
		
			return new ModelAndView("redirect:/clientes/form");
		} catch (DocumentoExistenteException e) {
			result.rejectValue("documento", e.getMessage(), e.getMessage());
			return novo(cliente);
		}
	}
	
	@GetMapping
	public ModelAndView pesquisar(ClienteFilter clienteFilter, BindingResult result, 
				@PageableDefault(size = 10) Pageable pageable, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("clientes/PesquisaClientes");
		
		PageWrapper<Cliente> pageWrapper = 
				new PageWrapper<>(clientes.filtrar(clienteFilter, pageable), request);
		
		mv.addObject("pagina", pageWrapper);
		return mv;
	}
	
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Cliente> pesquisar(String nome) {
		validarNome(nome);
		return clientes.findByNomeStartingWithIgnoreCase(nome);
	}
	
	private void validarNome(String nome) {
		if (StringUtils.isEmpty(nome) || nome.length() < 3)
			throw new IllegalArgumentException();
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
	
}