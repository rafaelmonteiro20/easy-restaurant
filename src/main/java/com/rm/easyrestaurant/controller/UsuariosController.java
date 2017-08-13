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

import com.rm.easyrestaurant.model.Usuario;
import com.rm.easyrestaurant.service.UsuarioService;
import com.rm.easyrestaurant.service.exception.EmailUsuarioJaCadastradoException;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/form")
	public ModelAndView form(Usuario usuario) {
		return new ModelAndView("usuario/CadastroUsuario");
	}
	
	@PostMapping("/form")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors())
			return form(usuario);
		
		try {
			usuarioService.save(usuario);
		
			attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
			return new ModelAndView("redirect:/usuarios/form");
		} catch (EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return form(usuario);
		}
	}
}