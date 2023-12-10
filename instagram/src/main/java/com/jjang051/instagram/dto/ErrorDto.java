package com.jjang051.instagram.dto;



import com.jjang051.instagram.code.ErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    private ErrorCode errorCode;
    private String errorMessage;
}