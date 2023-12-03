package com.jjang051.photogram02.handler;


import com.jjang051.photogram02.dto.CommonResponseDto;
import com.jjang051.photogram02.handler.exception.CustomApiException;
import com.jjang051.photogram02.handler.exception.CustomException;
import com.jjang051.photogram02.handler.exception.CustomValidationApiException;
import com.jjang051.photogram02.handler.exception.CustomValidationException;
import com.jjang051.photogram02.util.Script;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHanlder {
 
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		// CMRespDto, Script 비교
		// 1. 클라이언트에게 응답할 때는 Script 좋음.
		// 2. Ajax통신 - CMRespDto
		// 3. Android 통신 - CMRespDto
		if(e.getErrorMap() == null) {
			return Script.back(e.getMessage());
		}else {
			return Script.back(e.getErrorMap().toString());
		}

	}

/*	@ExceptionHandler(CustomValidationException.class)
	public Map<String,String> validationException(CustomValidationException e) {
		return e.getErrorMap();

	}*/
	
	@ExceptionHandler(CustomException.class)
	public String exception(CustomException e) {
		return Script.back(e.getMessage());
	}

	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
		return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<?> apiException(CustomApiException e) {
		return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
	}
}
