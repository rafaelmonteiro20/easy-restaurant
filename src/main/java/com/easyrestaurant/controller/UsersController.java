package com.easyrestaurant.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easyrestaurant.controller.page.PageWrapper;
import com.easyrestaurant.model.User;
import com.easyrestaurant.repository.Groups;
import com.easyrestaurant.repository.Users;
import com.easyrestaurant.repository.filter.UserFilter;
import com.easyrestaurant.service.UsuarioService;
import com.easyrestaurant.service.exception.ExistingRecordException;

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
			return form(user);
		}
		
		try {
			userService.save(user);
			attributes.addFlashAttribute("message", "Usuário salvo com sucesso");
			return new ModelAndView("redirect:/users");
		} catch (ExistingRecordException e) {
			result.rejectValue("mail", e.getMessage(), e.getMessage());
		} catch (IllegalArgumentException e) {
			result.rejectValue("password", e.getMessage(), e.getMessage());
		}
		
		return form(user);
	}
	
	@GetMapping
	public ModelAndView search(UserFilter userFilter, @PageableDefault(size = 3) Pageable pageable, 
			HttpServletRequest request) {
		
		Page<User> page = users.findAll(userFilter, pageable);
		PageWrapper<User> pageWrapper = new PageWrapper<>(page, request);
		
		ModelAndView mv = new ModelAndView("/user/user-list");
		mv.addObject("groups", groups.findAll());
		mv.addObject("page", pageWrapper);
		return mv;
	}
	
//	@PutMapping("/status")
//	@ResponseStatus(HttpStatus.OK)
//	public void atualizarStatus(@RequestParam("codigos[]") Long[] codigos, 
//			@RequestParam("status") StatusUsuario statusUsuario) {
//		
//		userService.alterarStatus(codigos, statusUsuario);
//	}
	
}
