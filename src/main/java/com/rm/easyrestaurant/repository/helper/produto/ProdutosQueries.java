package com.rm.easyrestaurant.repository.helper.produto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rm.easyrestaurant.dto.ProdutoDTO;
import com.rm.easyrestaurant.model.Produto;
import com.rm.easyrestaurant.repository.filter.ProdutoFilter;

public interface ProdutosQueries {

	Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable);

	List<ProdutoDTO> porSkuOuNome(String SkuOuNome);
	
}