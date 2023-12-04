package com.jjang051.jpa.exception;

public class NotFoundMember extends RuntimeException {
    public NotFoundMember(String message) {
        super(message);
    }
}
