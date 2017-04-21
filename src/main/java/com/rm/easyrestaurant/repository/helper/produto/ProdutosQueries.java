package com.rm.easyrestaurant.repository.helper.produto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.rm.easyrestaurant.model.Produto;
import com.rm.easyrestaurant.repository.filter.ProdutoFilter;

public interface ProdutosQueries {

	List<Produto> filtrar(ProdutoFilter filtro, Pageable pageable);
	
}
