package com.gadgets.exception;



public class ConcurrencyException extends Exception {
    
	private static final long serialVersionUID = 1L;

	public ConcurrencyException(String message) {
        super(message);
    }
}
