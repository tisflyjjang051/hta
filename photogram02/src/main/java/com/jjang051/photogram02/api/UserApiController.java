package com.jjang051.photogram02.api;

import com.jjang051.photogram02.dto.CommonResponseDto;
import com.jjang051.photogram02.dto.CustomUserDetailsDto;
import com.jjang051.photogram02.dto.UserUpdateDto;
import com.jjang051.photogram02.entity.User;
import com.jjang051.photogram02.handler.exception.CustomValidationApiException;
import com.jjang051.photogram02.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {

    private final UserService userService;
    @PutMapping("/api/user/{id}")
    public CommonResponseDto<?> update(@PathVariable int id,
                                       @Valid
                               @ModelAttribute UserUpdateDto userUpdateDto,
                                       BindingResult bindingResult,
                                       @AuthenticationPrincipal CustomUserDetailsDto customUserDetailsDto) {
        log.info("userUpdateDto==={}",userUpdateDto);
        if(bindingResult.hasErrors()) {
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError error:bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패함.",errorMap);
        }
        User userEntity = userService.updateUserInfo(id,userUpdateDto.toEntity());
        customUserDetailsDto.setLoggedUser(userEntity);
        return new CommonResponseDto<>(1,"회원수정 완료",userEntity);
    }

}
