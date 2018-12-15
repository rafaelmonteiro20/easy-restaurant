package com.easyrestaurant.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easyrestaurant.controller.page.PageWrapper;
import com.easyrestaurant.model.Usuario;
import com.easyrestaurant.repository.Grupos;
import com.easyrestaurant.repository.Usuarios;
import com.easyrestaurant.repository.filter.UsuarioFilter;
import com.easyrestaurant.service.StatusUsuario;
import com.easyrestaurant.service.UsuarioService;
import com.easyrestaurant.service.exception.EmailUsuarioJaCadastradoException;
import com.easyrestaurant.service.exception.SenhaObrigatoriaUsuarioException;

//@Controller
//@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private Grupos grupos;
	

	@GetMapping("/form")
	public ModelAndView form(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		mv.addObject("grupos", grupos.findAll());
		return mv;
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
		} catch (SenhaObrigatoriaUsuarioException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
		}
		
		return form(usuario);
	}
	
	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter, 
		@PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		
		PageWrapper<Usuario> paginaWrapper = new PageWrapper<>(usuarios.pesquisar(usuarioFilter, pageable),
				httpServletRequest);
		
		ModelAndView mv = new ModelAndView("/usuario/PesquisaUsuarios");
		mv.addObject("grupos", grupos.findAll());
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@PutMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public void atualizarStatus(@RequestParam("codigos[]") Long[] codigos, 
			@RequestParam("status") StatusUsuario statusUsuario) {
		
		usuarioService.alterarStatus(codigos, statusUsuario);
	}
	
}