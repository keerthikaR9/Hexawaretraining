package com.gadgets.exception;

public class OrderProcessingException extends Exception {
    
	private static final long serialVersionUID = 1L;

	public OrderProcessingException(String message) {
        super(message);
    }
	public OrderProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
