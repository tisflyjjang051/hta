package com.jjang051.photogram02.controller;

import com.jjang051.photogram02.dto.CustomUserDetailsDto;
import com.jjang051.photogram02.dto.UserProfileDto;
import com.jjang051.photogram02.entity.User;
import com.jjang051.photogram02.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal CustomUserDetailsDto customUserDetailsDto) {
        //UserProfileDto dto = userService.profile(pageUserId, principalDetails.getUser().getId());
        User userEntity = userService.userProfile(pageUserId);
        model.addAttribute("userInfo", userEntity);
        return "/user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String updateForm(@PathVariable int id, @AuthenticationPrincipal CustomUserDetailsDto customUserDetailsDto, Model model) {
        // 1. 추천
        //System.out.println("세션 정보 : "+principalDetails.getUser());

        // 2. 극혐
        Authentication auth =   SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsDto mPrincipalDetails = (CustomUserDetailsDto) auth.getPrincipal();
       // System.out.println("직접 찾은 세션 정보 : "+mPrincipalDetails.getUser());
        log.info("customUserDetailsDto.toString()==={}",customUserDetailsDto.getLoggedUser().toString());
        model.addAttribute("myInfo",customUserDetailsDto.getLoggedUser());
        return "/user/update";
    }

}
