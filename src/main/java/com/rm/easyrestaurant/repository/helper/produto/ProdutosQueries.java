package com.rm.easyrestaurant.repository.helper.produto;

import java.util.List;

import com.rm.easyrestaurant.model.Produto;
import com.rm.easyrestaurant.repository.filter.ProdutoFilter;

public interface ProdutosQueries {

	List<Produto> filtrar(ProdutoFilter filtro);
	
}
