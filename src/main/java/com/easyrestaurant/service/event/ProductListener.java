package com.easyrestaurant.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.easyrestaurant.storage.ImageStorage;

@Component
public class ProductListener {

	@Autowired
	private ImageStorage imageStorage;
	
	@EventListener(condition = "#event.hasImage()")
	public void productSaved(ProductSavedEvent event) {
		imageStorage.save(event.getProduct().getImage());
	}
	
}
