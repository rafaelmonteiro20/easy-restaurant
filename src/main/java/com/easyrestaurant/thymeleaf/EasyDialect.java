package com.easyrestaurant.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.easyrestaurant.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.easyrestaurant.thymeleaf.processor.MessageElementTagProcessor;
import com.easyrestaurant.thymeleaf.processor.OrderElementTagProcessor;
import com.easyrestaurant.thymeleaf.processor.PaginationElementTagProcessor;

public class EasyDialect extends AbstractProcessorDialect {

	public EasyDialect() {
		super("Easy Restaurant", "easy", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
//		processadores.add(new MessageElementTagProcessor(dialectPrefix));
//		processadores.add(new OrderElementTagProcessor(dialectPrefix));
//		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		return processadores;
	}

}