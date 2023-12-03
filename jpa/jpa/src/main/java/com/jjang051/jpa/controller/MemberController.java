package com.jjang051.jpa.controller;

import com.jjang051.jpa.dto.MemberDto;
import com.jjang051.jpa.entity.Member02;
import com.jjang051.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/join")
    public String join() {
        return "/member/join";
    }
    @GetMapping("/mypage")
    public String mypage(@RequestParam String id,Model model) {
        //jfkjdksf?id=jjang
        MemberDto memberInfo = memberService.getMemberInfo(id);
        model.addAttribute("memberInfo",memberInfo);
        return "/member/mypage";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam String id,Model model) {
        MemberDto memberInfo = memberService.getMemberInfo(id);
        model.addAttribute("memberInfo",memberInfo);
        return "/member/modify";
    }


    @PostMapping("/modify")
    public String modifyProcess(@ModelAttribute MemberDto memberDto, Model model) {
        MemberDto memberInfo = memberService.modifyMember(memberDto);
        //model.addAttribute("memberInfo",memberInfo);
        return "redirect:/";
    }

    @PostMapping("/join")
    public String joinProcess(MemberDto memberDto) {
        memberService.join(memberDto);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<MemberDto> memberList = memberService.getAllMember();
        model.addAttribute("memberList",memberList);
        return "/member/list";
    }

    @GetMapping("/delete")
    public String list(@RequestParam String id) {
        memberService.deleteMember(id);
        return "/member/list";
    }
}
