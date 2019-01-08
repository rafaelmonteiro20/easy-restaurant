package com.easyrestaurant.service.event;

import org.springframework.util.StringUtils;

import com.easyrestaurant.model.Product;

public class ProductSavedEvent {

	private Product product;

	public ProductSavedEvent(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}
	
	public boolean hasImage() {
		return !StringUtils.isEmpty(product.getImage());
	}
	
}
