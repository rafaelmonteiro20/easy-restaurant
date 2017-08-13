package com.rm.easyrestaurant.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.rm.easyrestaurant.model.Grupo;

public class GrupoConverter implements Converter<String, Grupo> {

	@Override
	public Grupo convert(String codigo) {
		if (StringUtils.isEmpty(codigo))
			return null;
		
		return new Grupo(Long.valueOf(codigo));
	}

}