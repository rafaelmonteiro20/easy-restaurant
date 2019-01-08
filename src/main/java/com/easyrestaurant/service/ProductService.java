package com.easyrestaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyrestaurant.model.Product;
import com.easyrestaurant.repository.Products;
import com.easyrestaurant.service.event.ProductSavedEvent;

@Service
public class ProductService {

	@Autowired
	private Products products;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void save(Product product) {
		products.save(product);
		publisher.publishEvent(new ProductSavedEvent(product));
	}
	
}
