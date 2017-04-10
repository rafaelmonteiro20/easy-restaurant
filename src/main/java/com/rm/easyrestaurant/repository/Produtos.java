package com.rm.easyrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rm.easyrestaurant.model.Produto;

public interface Produtos extends JpaRepository<Produto, Long> {

}