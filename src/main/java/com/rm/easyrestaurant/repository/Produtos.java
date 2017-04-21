package com.rm.easyrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rm.easyrestaurant.model.Produto;
import com.rm.easyrestaurant.repository.helper.produto.ProdutosQueries;

@Repository
public interface Produtos extends JpaRepository<Produto, Long>, ProdutosQueries {

	
}