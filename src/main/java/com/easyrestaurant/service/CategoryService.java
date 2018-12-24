package com.easyrestaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyrestaurant.model.Category;
import com.easyrestaurant.repository.Categories;
import com.easyrestaurant.repository.filter.CategoryFilter;
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
	
	@Transactional(readOnly = true)
	public List<Category> findAll(CategoryFilter filter) {
		
		Category category = new Category();
		category.setName(filter.getName());
		
		ExampleMatcher matcher = ExampleMatcher.matching()
			.withMatcher("name", match -> match.ignoreCase())
			.withMatcher("name", match -> match.contains());
		
		Example<Category> example = Example.of(category, matcher);
		
		return categories.findAll(example, Sort.by("name"));
	}
	
}
