package com.jjang051.instagram.exception;

import com.jjang051.instagram.code.ErrorCode;
import lombok.Getter;

@Getter
public class BoardException extends RuntimeException {
    private String detailMessage;
    private ErrorCode errorCode;

    public BoardException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    public BoardException(ErrorCode errorCode, String detailMessage) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = detailMessage;
    }
}
