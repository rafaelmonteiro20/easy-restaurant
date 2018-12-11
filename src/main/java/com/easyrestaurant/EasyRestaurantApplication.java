package com.easyrestaurant;

import java.math.BigDecimal;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.easyrestaurant.controller.converter.CategoriaConverter;
import com.easyrestaurant.controller.converter.GrupoConverter;
import com.easyrestaurant.thymeleaf.EasyDialect;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@SpringBootApplication
public class EasyRestaurantApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(EasyRestaurantApplication.class, args);
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		
		NumberStyleFormatter decimalFormatter = new NumberStyleFormatter("#,##0.00");
		registry.addFormatterForFieldType(BigDecimal.class, decimalFormatter);
	
		NumberStyleFormatter integerFormatter = new NumberStyleFormatter("#,##0");
		registry.addFormatterForFieldType(Integer.class, integerFormatter);
	}
	
	
//	@Bean
//	public LocaleResolver localeResolver() {
//		return new FixedLocaleResolver(new Locale("pt", "BR"));
//	}
//	
//	@Bean
//	public TemplateEngine templateEngine() {
//		SpringTemplateEngine engine = new SpringTemplateEngine();
//		engine.addDialect(new LayoutDialect());
//		engine.addDialect(new EasyDialect());
//		return engine;
//	}
	
//	@Bean
//	public LayoutDialect layoutDialect() {
//	    return new LayoutDialect();
//	}

//	@Bean
//	public EasyDialect easyDialect() {
//		return new EasyDialect();
//	}
	
}
