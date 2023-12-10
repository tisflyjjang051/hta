package com.jjang051.instagram.api;

import com.jjang051.instagram.dto.CommonResponseDto;
import com.jjang051.instagram.dto.CustomUserDetails;
import com.jjang051.instagram.dto.MemberUpdateDto;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.exception.CustomValidationApiException;
import com.jjang051.instagram.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {

    private final MemberService memberService;
    @PutMapping("/api/member/{id}")
    public CommonResponseDto<?> update(@PathVariable int id,
                                       @Valid
                                       @ModelAttribute MemberUpdateDto memberUpdateDto,
                                       BindingResult bindingResult,
                                       @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("userUpdateDto==={}",memberUpdateDto.toString());
        if(bindingResult.hasErrors()) {
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError error:bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패함.",errorMap);
        }
        Member memberEntity = memberService.updateMemberInfo(memberUpdateDto.toEntity());
        customUserDetails.setLoggedMember(memberEntity);
        return new CommonResponseDto<>(1,"회원수정 완료",memberEntity);
    }


    @PutMapping("/api/member/{principalId}/profileImageUrl")
    public ResponseEntity<?> profileImageUrlUpdate(@PathVariable int principalId, MultipartFile profileImageFile,
                                                   @AuthenticationPrincipal CustomUserDetails customUserDetails){
        Member memberEntity = memberService.changeProfile(principalId, profileImageFile);
        customUserDetails.setLoggedMember(memberEntity); // 세션 변경
        return new ResponseEntity<>(new CommonResponseDto<>(1, "프로필사진변경 성공", null), HttpStatus.OK);
    }
}
