package com.jjang051.instagram.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 내가 만든 오류들....
    INVALID_REQUEST("잘못된 요청입니다."),
    DUPLICATE_MEMBER("이미 가입된 회원입니다."),
    DUPLICATE_EMAIL("쓸수 없는 이메일입니다."),

    BAD_NAME("이름에 욕이 들어갈 수 없습니다. 착한 어린이가 되어 봅시다."),
    INTERNAL_SERVER_ERROR("서버에 오류가 발생하였습니다."),
    NOT_FOUND_USER("해당하는 아이디가 없습니다.");

    private final String message;
}
