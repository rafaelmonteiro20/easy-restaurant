package com.easyrestaurant.controller;

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

import com.easyrestaurant.controller.page.PageWrapper;
import com.easyrestaurant.model.Customer;
import com.easyrestaurant.model.TypePerson;
import com.easyrestaurant.repository.Customers;
import com.easyrestaurant.repository.filter.CustomerFilter;
import com.easyrestaurant.service.CustomerService;
import com.easyrestaurant.service.exception.ExistingRecordException;

@Controller
@RequestMapping("/customers")
public class CustomersController {

	@Autowired
	private Customers customers;
	
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/form")
	public ModelAndView form(Customer Customer) {
		ModelAndView mv = new ModelAndView("customer/customer-form");
		mv.addObject("typesPerson", TypePerson.values());
		return mv;
	}
	
	@PostMapping("/form")
	public ModelAndView save(@Valid Customer customer, BindingResult result, RedirectAttributes attributes) {
		System.out.println("Chegou... " + customer);
		System.out.println("Tem error? " + result.hasErrors());
		
		if(result.hasErrors()) {
			return form(customer);
		}
		
		try {
			customerService.save(customer);
			attributes.addFlashAttribute("message", "Cliente cadastrado com sucesso.");
		
			return new ModelAndView("redirect:/customers");
		} catch (ExistingRecordException e) {
			result.rejectValue("document", e.getMessage(), e.getMessage());
			return form(customer);
		}
	}
	
	@GetMapping
	public ModelAndView search(CustomerFilter customerFilter, BindingResult result, 
				@PageableDefault(size = 10) Pageable pageable, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("customer/customer-list");
		
		PageWrapper<Customer> pageWrapper = 
				new PageWrapper<>(customers.findAll(customerFilter, pageable), request);
		
		mv.addObject("page", pageWrapper);
		return mv;
	}
	
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Customer> search(String name) {
		if (StringUtils.isEmpty(name) || name.length() < 3) {
			throw new IllegalArgumentException();
		}

		return customers.findByNameStartingWithIgnoreCase(name);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> handleIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
	
}
