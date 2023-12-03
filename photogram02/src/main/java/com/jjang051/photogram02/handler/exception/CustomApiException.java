package com.jjang051.photogram02.handler.exception;

import java.util.Map;

public class CustomApiException extends RuntimeException{

	private Map<String, String> errorMap;

	public CustomApiException(String message) {
		super(message);
	}

	public CustomApiException(String message, Map<String, String> errorMap) {
		super(message);
		this.errorMap = errorMap;
	}
}
