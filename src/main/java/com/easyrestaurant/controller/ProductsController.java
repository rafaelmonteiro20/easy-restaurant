package com.easyrestaurant.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easyrestaurant.controller.page.PageWrapper;
import com.easyrestaurant.dto.ProductDTO;
import com.easyrestaurant.model.Product;
import com.easyrestaurant.repository.Categories;
import com.easyrestaurant.repository.Products;
import com.easyrestaurant.repository.filter.ProductFilter;
import com.easyrestaurant.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private Categories categories;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private Products products;
	
	@GetMapping("/form")
	public ModelAndView form(Product product) {
		ModelAndView mv = new ModelAndView("product/product-form");
		mv.addObject("categories", categories.findAll());
		return mv;
	}
	
	@PostMapping(value = "/form")
	public ModelAndView save(@Valid Product product, BindingResult result, Model model, 
			RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return form(product);
		}
		
		productService.save(product);
		
		attributes.addFlashAttribute("message", "Cadastro realizado com sucesso.");
		return new ModelAndView("redirect:/products");
	}
	
	@GetMapping
	public ModelAndView search(ProductFilter productFilter, @PageableDefault(size = 2) Pageable pageable, 
			HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("product/product-list");
		mv.addObject("categories", categories.findAll(Sort.by("name")));
		
		PageWrapper<Product> pageWrapper = 
				new PageWrapper<>(products.findAll(productFilter, pageable), request);
		
		mv.addObject("page", pageWrapper);
		return mv;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProductDTO> search(String skuOrName) {
		return products.findBySkuOrName(skuOrName);
	}
	
}
