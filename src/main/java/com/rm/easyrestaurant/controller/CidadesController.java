package com.rm.easyrestaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rm.easyrestaurant.model.Cidade;
import com.rm.easyrestaurant.model.Estado;
import com.rm.easyrestaurant.repository.Cidades;

@Controller
@RequestMapping("/cidades")
public class CidadesController {

	@Autowired
	private Cidades cidades;
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorEstado(
		@RequestParam(name = "estado") Estado estado) {
		
		return cidades.findByEstado(estado);
	}
	
}