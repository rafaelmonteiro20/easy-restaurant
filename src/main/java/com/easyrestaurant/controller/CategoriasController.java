package com.easyrestaurant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easyrestaurant.model.Categoria;
import com.easyrestaurant.repository.Categorias;
import com.easyrestaurant.service.CategoriaService;
import com.easyrestaurant.service.exception.CategoriaExistenteException;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {

	@Autowired
	private CategoriaService service;
	
	@Autowired
	private Categorias categorias;
	
	@GetMapping("/form")
	public ModelAndView form(Categoria categoria) {
		return new ModelAndView("/categoria/CadastraCategoria");
	}
	
	@PostMapping("/form")
	public ModelAndView salvar(@Valid Categoria categoria, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors())
			return form(categoria);
		
		try {
			service.save(categoria);
			attributes.addFlashAttribute("mensagem", "Categoria salva com sucesso.");
			return new ModelAndView("redirect:/categorias/form");

		} catch (CategoriaExistenteException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return form(categoria);
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> save(@RequestBody @Valid Categoria categoria, BindingResult result) {
		if(result.hasErrors()) {
			String message = result.getFieldError("nome").getDefaultMessage();
			return ResponseEntity.badRequest().body(message);
		}
		
		service.save(categoria);
		
		return ResponseEntity.ok(categoria);
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("/categoria/PesquisaCategorias");
		mv.addObject("categorias", categorias.findAll());
		return mv;
	}
	
}