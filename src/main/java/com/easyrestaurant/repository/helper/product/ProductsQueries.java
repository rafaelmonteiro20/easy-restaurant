package com.easyrestaurant.repository.helper.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.easyrestaurant.dto.ProductDTO;
import com.easyrestaurant.model.Product;
import com.easyrestaurant.repository.filter.ProductFilter;

public interface ProductsQueries {

	Page<Product> findAll(ProductFilter filter, Pageable pageable);

	List<ProductDTO> findBySkuOrName(String skuOrName);
	
}
