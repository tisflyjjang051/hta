package com.jjang051.photogram02.handler.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomValidationException extends RuntimeException{
	
	private Map<String, String> errorMap;
	
	public CustomValidationException(String message, Map<String, String> errorMap) {
		super(message);
		this.errorMap = errorMap;
	}
	
//	public Map<String, String> getErrorMap(){
//		return errorMap;
//	}
}
