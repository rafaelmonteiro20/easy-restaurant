package com.rm.easyrestaurant.exception;

public class DocumentoExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DocumentoExistenteException(String msg) {
		super(msg);
	}

}