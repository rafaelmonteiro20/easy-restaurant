package com.easyrestaurant.config;

import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.easyrestaurant.storage.ImageStorage;
import com.easyrestaurant.storage.impl.ImageStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = ImageStorage.class)
public class ServiceConfig {

	@Bean
	public ImageStorage imageStorage() {
		return new ImageStorageLocal(Paths.get("C:/easy-images"));
	}
	
}
