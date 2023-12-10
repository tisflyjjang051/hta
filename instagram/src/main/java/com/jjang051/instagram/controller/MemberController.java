package com.jjang051.instagram.controller;

import com.jjang051.instagram.dto.CustomUserDetails;
import com.jjang051.instagram.dto.JoinDto;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/mypage/{pageUserId}")
    public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Member memberInfo = memberService.getProfile(pageUserId);
        model.addAttribute("memberInfo", memberInfo);
        log.info(memberInfo.getUserId());
        return "/member/mypage";
    }

    @GetMapping("/{pageUserId}/modify")
    public String modify(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Member memberInfo = memberService.getProfile(pageUserId);
        model.addAttribute("memberInfo", memberInfo);
        return "/member/update";
    }

}
