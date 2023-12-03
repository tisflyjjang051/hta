package com.jjang051.photogram02.controller;

import com.jjang051.photogram02.dto.SignupDto;
import com.jjang051.photogram02.entity.User;
import com.jjang051.photogram02.handler.exception.CustomValidationException;
import com.jjang051.photogram02.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/auth")
@RequiredArgsConstructor
@Controller
@Slf4j
public class AuthController {

    private final AuthService authService;





    @GetMapping("/signin")
    public String signinForm() {
        return "/auth/signin";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "/auth/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute SignupDto signupDto, BindingResult bindingResult) { // key=value (x-www-form-urlencoded)

        if(bindingResult.hasErrors()) {
            Map<String,String> errorMap =  new HashMap<>();
            for(FieldError error:bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(),error.getDefaultMessage());
                log.info(error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패",errorMap);
        }

        // User < - SignupDto
        User user = signupDto.toEntity();
        authService.join(user);
        // System.out.println(userEntity);

        // 로그를 남기는 후처리!!
        return "/auth/signin";

    }
}
