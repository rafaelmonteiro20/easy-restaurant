package com.rm.easyrestaurant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rm.easyrestaurant.model.Categoria;
import com.rm.easyrestaurant.repository.Categorias;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {

	@Autowired
	private Categorias categorias;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> save(@RequestBody @Valid Categoria categoria, BindingResult result) {
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		System.out.println("Categoria::" + categoria.getNome());
		
		return ResponseEntity.ok(categoria);
	}
	
}