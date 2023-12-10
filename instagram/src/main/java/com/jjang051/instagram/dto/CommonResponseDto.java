package com.jjang051.instagram.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResponseDto<T> {
	private int code; // 1(성공), -1(실패)
	private String message;
	private T data;
}
