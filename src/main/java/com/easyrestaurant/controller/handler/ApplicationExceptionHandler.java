package com.easyrestaurant.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.easyrestaurant.service.exception.ExistingRecordException;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<String> handleCategoriaExistenteException(ExistingRecordException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
}
