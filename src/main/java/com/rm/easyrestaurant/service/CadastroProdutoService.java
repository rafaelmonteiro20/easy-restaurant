package com.rm.easyrestaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rm.easyrestaurant.model.Produto;
import com.rm.easyrestaurant.repository.Produtos;

@Service
public class CadastroProdutoService {

	@Autowired
	private Produtos produtos;
	
	@Transactional
	public void save(Produto produto) {
		produtos.save(produto);
	}
	
}