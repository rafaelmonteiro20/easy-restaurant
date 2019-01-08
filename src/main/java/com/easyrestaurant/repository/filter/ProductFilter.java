package com.easyrestaurant.repository.filter;

import com.easyrestaurant.model.Category;

public class ProductFilter {

	private String sku;
	private String name;
	private Category category;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

}
