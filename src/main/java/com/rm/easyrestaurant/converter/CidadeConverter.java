package com.rm.easyrestaurant.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.rm.easyrestaurant.model.Cidade;

public class CidadeConverter implements Converter<String, Cidade> {

	@Override
	public Cidade convert(String codigo) {
		if(StringUtils.isEmpty(codigo))
			return null;
			
		return new Cidade(Long.valueOf(codigo));
	}

}