package com.jjang051.board.exception;

import com.jjang051.board.code.ErrorCode;
import lombok.Getter;

@Getter
public class MemberException extends RuntimeException{
    private String detailMessage;
    private ErrorCode errorCode;

    public MemberException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    public MemberException(ErrorCode errorCode, String detailMessage) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = detailMessage;
    }
}
