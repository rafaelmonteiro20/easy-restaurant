package com.easyrestaurant;

import java.math.BigDecimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.easyrestaurant.controller.converter.CategoryConverter;
import com.easyrestaurant.controller.converter.GroupConverter;

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
		
		registry.addConverter(new CategoryConverter());
		registry.addConverter(new GroupConverter());
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
