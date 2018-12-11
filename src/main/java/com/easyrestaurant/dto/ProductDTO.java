package com.easyrestaurant.dto;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

public class ProductDTO {

	private Long id;
	private String sku;
	private String name;
	private String category;
	private BigDecimal price;
	private String image;

	public ProductDTO(Long id, String sku, String name, String category, BigDecimal price, String image) {
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.price = price;
		this.category = category;
		this.image = StringUtils.isEmpty(image) ? "produto-mock.png" : image;
	}

	public Long getId() {
		return id;
	}

	public String getSku() {
		return sku;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getImage() {
		return image;
	}
	
}
