package com.easyrestaurant.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.easyrestaurant.model.Category;

public class CategoryConverter implements Converter<String, Category> {

	@Override
	public Category convert(String id) {
		if(StringUtils.isEmpty(id)) {
			return null;
		}
		
		return new Category(Long.valueOf(id));
	}

}
