package com.rm.easyrestaurant.exception;

public class CategoriaExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CategoriaExistenteException(String mensagem) {
		super(mensagem);
	}
	
}