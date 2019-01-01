package com.easyrestaurant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easyrestaurant.model.User;
import com.easyrestaurant.repository.Groups;
import com.easyrestaurant.repository.Users;
import com.easyrestaurant.service.UsuarioService;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private Users users;
	
	@Autowired
	private Groups groups;
	

	@GetMapping("/form")
	public ModelAndView form(User user) {
		ModelAndView mv = new ModelAndView("user/user-form");
		mv.addObject("groups", groups.findAll());
		return mv;
	}
	
	@PostMapping("/form")
	public ModelAndView salvar(@Valid User user, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return this.form(user);
		}
		
		System.out.println("Salvando... " + user);
		
//		try {
//			userService.save(usuario);
//		
//			attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
//			return new ModelAndView("redirect:/usuarios/form");
//		} catch (EmailUsuarioJaCadastradoException e) {
//			result.rejectValue("email", e.getMessage(), e.getMessage());
//		} catch (SenhaObrigatoriaUsuarioException e) {
//			result.rejectValue("senha", e.getMessage(), e.getMessage());
//		}
		
		return form(user);
	}
	
//	@GetMapping
//	public ModelAndView pesquisar(UserFilter usuarioFilter, 
//		@PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
//		
//		PageWrapper<User> paginaWrapper = new PageWrapper<>(users.pesquisar(usuarioFilter, pageable),
//				httpServletRequest);
//		
//		ModelAndView mv = new ModelAndView("/usuario/PesquisaUsuarios");
//		mv.addObject("grupos", groups.findAll());
//		mv.addObject("pagina", paginaWrapper);
//		
//		return mv;
//	}
//	
//	@PutMapping("/status")
//	@ResponseStatus(HttpStatus.OK)
//	public void atualizarStatus(@RequestParam("codigos[]") Long[] codigos, 
//			@RequestParam("status") StatusUsuario statusUsuario) {
//		
//		userService.alterarStatus(codigos, statusUsuario);
//	}
	
}