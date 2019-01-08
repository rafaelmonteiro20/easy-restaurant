package com.easyrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrestaurant.model.Order;

public interface Orders extends JpaRepository<Order, Long> {

}
