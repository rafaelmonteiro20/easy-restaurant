package com.easyrestaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pedidos")
public class PedidosController {

	@GetMapping("/form")
	public ModelAndView form() {
		return new ModelAndView("pedidos/CadastroPedido");
	}
	
}