package com.easyrestaurant.config;
import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.easyrestaurant.service.ProdutoService;
import com.easyrestaurant.storage.FotoStorage;
import com.easyrestaurant.storage.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = ProdutoService.class)
public class ServiceConfig {

	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal(Paths.get("C:/easyfotos"));
	}
	
}
