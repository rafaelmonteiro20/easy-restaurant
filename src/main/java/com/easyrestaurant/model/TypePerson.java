package com.easyrestaurant.model;

import com.easyrestaurant.model.validation.group.CNPJGroup;
import com.easyrestaurant.model.validation.group.CPFGroup;

public enum TypePerson {

	PHYSICAL("Física", "CPF", "000.000.000-00", CPFGroup.class) {
		@Override
		public String format(String document) {
			return document.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
		}
	}, 
	
	LEGAL("Jurídica", "CNPJ", "00.000.000/0000-00", CNPJGroup.class) {
		@Override
		public String format(String document) {
			return document.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})", "$1.$2.$3/$4-");
		}
	};

	private String description;
	private String document;
	private String mask;
	private Class<?> group;

	private TypePerson(String description, String document, String mask, Class<?> group) {
		this.description = description;
		this.document = document;
		this.mask = mask;
		this.group = group;
	}

	public String getDescription() {
		return description;
	}

	public String getDocument() {
		return document;
	}

	public String getMask() {
		return mask;
	}
	
	public Class<?> getGroup() {
		return group;
	}

	public static String removeFormatting(String document) {
		return document.replaceAll("\\.|/|-", "");
	}

	public abstract String format(String document);
	
}
