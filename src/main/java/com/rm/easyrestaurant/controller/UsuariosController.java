package com.rm.easyrestaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rm.easyrestaurant.model.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@GetMapping("/form")
	public ModelAndView form(Usuario usuario) {
		return new ModelAndView("usuario/CadastroUsuario");
	}
	
}