package com.easyrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyrestaurant.model.Product;
import com.easyrestaurant.repository.helper.product.ProductsQueries;

@Repository
public interface Products extends JpaRepository<Product, Long>, ProductsQueries {

}
