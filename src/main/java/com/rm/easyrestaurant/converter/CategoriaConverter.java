package com.rm.easyrestaurant.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.rm.easyrestaurant.model.Categoria;

public class CategoriaConverter implements Converter<String, Categoria> {

	@Override
	public Categoria convert(String codigo) {
		if(StringUtils.isEmpty(codigo))
			return null;
			
		return new Categoria(Long.valueOf(codigo));
	}

}