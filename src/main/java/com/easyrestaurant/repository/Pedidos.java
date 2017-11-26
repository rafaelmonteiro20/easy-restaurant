package com.easyrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrestaurant.model.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Long> {

}
