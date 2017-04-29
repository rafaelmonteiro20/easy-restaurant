package com.rm.easyrestaurant.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.rm.easyrestaurant.thymeleaf.processor.OrderElementTagProcessor;

public class EasyDialect extends AbstractProcessorDialect {

	public EasyDialect() {
		super("Easy Restaurant", "easy", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		return processadores;
	}

}