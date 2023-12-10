package com.jjang051.instagram.controller;

import com.jjang051.instagram.dto.JoinDto;
import com.jjang051.instagram.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }

    @PostMapping("/login")
    public void loginProcess() {
        log.info("여기를 타나");

    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("joinDto", new JoinDto());
        return "/auth/join";
    }

    @PostMapping("/join")
    public String joinProcess(@Valid @ModelAttribute JoinDto joinDto,
                              BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("joinDto",joinDto);
            return "/auth/join";
        }
        memberService.join(joinDto);
        return "redirect:/auth/login";
    }


}
