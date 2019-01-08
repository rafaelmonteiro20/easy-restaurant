package com.easyrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrestaurant.model.Product;
import com.easyrestaurant.repository.query.ProductsQueries;

public interface Products extends JpaRepository<Product, Long>, ProductsQueries {

}
