package com.easyrestaurant.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.easyrestaurant.model.Category;
import com.easyrestaurant.repository.Categories;
import com.easyrestaurant.service.exception.ExistingRecordException;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

	@Mock
	private Categories categories;
	
	private CategoryService categoryService;
	
	@Before
	public void init() {
		categoryService = new CategoryService(categories);
	}
	
	@Test
	public void whenNonexistentCategory_thenSave() throws Exception {
		
		Category category = new Category("Beef");
		
		when(categories.findByNameIgnoreCase("Beef")).thenReturn(Optional.empty());
		
		categoryService.save(category);
		
		verify(categories).saveAndFlush(category);
	}
	
	@Test(expected = ExistingRecordException.class)
	public void whenExistentCategory_thenThrowException() {
		Category category = new Category("juice");
		when(categories.findByNameIgnoreCase("juice")).thenReturn(Optional.of(new Category("Juice")));
		categoryService.save(category);
	}
	
}
