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

import com.easyrestaurant.model.Category;
import com.easyrestaurant.repository.filter.CategoryFilter;
import com.easyrestaurant.service.CategoryService;
import com.easyrestaurant.service.exception.ExistingRecordException;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/form")
	public ModelAndView form(Category category) {
		return new ModelAndView("/category/category-form");
	}
	
	@PostMapping("/form")
	public ModelAndView save(@Valid Category category, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return form(category);
		}
		
		try {
			categoryService.save(category);
			attributes.addFlashAttribute("message", "Categoria salva com sucesso.");
			return new ModelAndView("redirect:/categories");
		
		} catch (ExistingRecordException e) {
			result.rejectValue("name", e.getMessage(), e.getMessage());
			return form(category);
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> save(@RequestBody @Valid Category category, BindingResult result) {
		if(result.hasErrors()) {
			String message = result.getFieldError("name").getDefaultMessage();
			return ResponseEntity.badRequest().body(message);
		}

		categoryService.save(category);
		return ResponseEntity.ok(category);
	}
	
	@GetMapping
	public ModelAndView search(CategoryFilter categoryFilter) {
		ModelAndView mv = new ModelAndView("/category/category-list");
		mv.addObject("categories", categoryService.findAll(categoryFilter));
		return mv;
	}
	
}
