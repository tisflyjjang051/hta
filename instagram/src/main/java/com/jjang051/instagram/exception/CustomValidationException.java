package com.jjang051.instagram.exception;

import java.util.Map;

public class CustomValidationException extends Throwable {

    private Map<String, String> errorMap;
    public CustomValidationException(String message) {
        super(message);
    }


    public CustomValidationException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }
}
