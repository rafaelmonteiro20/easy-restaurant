package com.rm.easyrestaurant.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.rm.easyrestaurant.service.CadastroProdutoService;

@Configuration
@ComponentScan(basePackageClasses = CadastroProdutoService.class)
public class ServiceConfig {

}