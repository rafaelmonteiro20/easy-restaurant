package com.easyrestaurant.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyrestaurant.model.Category;
import com.easyrestaurant.repository.Categories;
import com.easyrestaurant.service.exception.ExistingRecordException;

@Service
public class CategoryService {

	private Categories categories;

	public CategoryService(Categories categories) {
		this.categories = categories;
	}
	
	@Transactional
	public Category save(Category category) {
		Optional<Category> optional = categories.findByNameIgnoreCase(category.getName());
		
		if(optional.isPresent()) {
			throw new ExistingRecordException("Categoria já cadastrada.");
		}
		
		return categories.saveAndFlush(category);
	}
	
}
