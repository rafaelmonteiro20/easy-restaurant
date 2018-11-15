package com.easyrestaurant.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.easyrestaurant.service.exception.CategoriaExistenteException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<String> handleCategoriaExistenteException(CategoriaExistenteException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
}
