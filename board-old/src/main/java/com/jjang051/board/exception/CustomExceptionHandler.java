package com.jjang051.board.exception;

import com.jjang051.board.code.ErrorCode;
import com.jjang051.board.dto.ErrorDto;
import com.jjang051.board.dto.JoinDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;


@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    // 여기에 에러 다 모아서 쓰기....
    @ExceptionHandler(BoardException.class)
    public String runtimeHandle(BoardException e) {
        ErrorDto response = ErrorDto.builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
        return "/errors/error";
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public ErrorDto duplicateMember(SQLException e) {
        ErrorDto response = ErrorDto.builder()
                .errorCode(ErrorCode.DUPLICATE_MEMBER)
                .errorMessage(ErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();
        return response;
    }

    /*
    @ExceptionHandler(MemberException.class)
    @ResponseBody
    public ErrorDto memberHandler(MemberException e) {
        ErrorDto response = ErrorDto.builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getMessage())
                .build();
        return response;
    }
    */
    @ExceptionHandler(MemberException.class)
    public String memberHandler(MemberException e, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        ErrorDto response = ErrorDto.builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getMessage())
                .build();
        model.addAttribute("error",response);
        model.addAttribute("joinDto",new JoinDto());
        log.info("==============={}",request.getRequestURI());
        //return "/errors/error";
        return ""+request.getRequestURI();
    }



    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ErrorDto notFound(UsernameNotFoundException e) {
        ErrorDto response = ErrorDto.builder()
                .errorCode(ErrorCode.NOT_FOUND)
                .errorMessage(ErrorCode.NOT_FOUND.getMessage())
                .build();
        return response;
    }
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ErrorDto anonymousException(RuntimeException e) {
        ErrorDto response = ErrorDto.builder()
                .errorCode(ErrorCode.INTERNAL_SERVER_ERROR)
                .errorMessage(ErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();
        return response;
    }
}
