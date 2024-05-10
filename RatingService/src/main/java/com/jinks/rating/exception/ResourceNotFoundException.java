package com.jinks.rating.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String s) {
		super(s);
	}
	
	public ResourceNotFoundException() {
		super("not found !!!");
	}

}
