package com.easyrestaurant.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyrestaurant.model.ItemOrder;
import com.easyrestaurant.model.Order;
import com.easyrestaurant.repository.Orders;

@Service
public class OrderService {
	
	@Autowired
	private Orders orders;

	@Transactional
	public void save(Order order) {
		
		if(order.isNew()) {
			order.setCreatedOn(LocalDateTime.now());
		}
		
		order.setTotalPrice(totalCalculates(order));
		orders.save(order);
	}

	private BigDecimal totalCalculates(Order order) {
		return order.getItems().stream()
				.map(ItemOrder::getTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
