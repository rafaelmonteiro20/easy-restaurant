package com.easyrestaurant.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.easyrestaurant.model.Group;

public class GroupConverter implements Converter<String, Group> {

	@Override
	public Group convert(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		
		return new Group(Long.valueOf(id));
	}

}
