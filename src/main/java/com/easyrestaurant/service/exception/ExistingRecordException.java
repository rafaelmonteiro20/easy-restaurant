package com.easyrestaurant.service.exception;

public class ExistingRecordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExistingRecordException(String message) {
		super(message);
	}
	
}
