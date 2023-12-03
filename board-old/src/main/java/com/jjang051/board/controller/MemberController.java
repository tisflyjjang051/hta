package com.jjang051.board.controller;

import com.jjang051.board.dto.CustomUserDetails;
import com.jjang051.board.dto.JoinDto;
import com.jjang051.board.dto.LoginDto;
import com.jjang051.board.dto.ModalDto;
import com.jjang051.board.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login( @RequestParam(value = "templates/error", required = false) String error,
                         @RequestParam(value = "exception", required = false) String exception,Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        model.addAttribute("loginDto",new LoginDto());
        return "/member/login";
    }
 /*   @PostMapping("/login")
    public String loginProcess(@Valid @ModelAttribute LoginDto loginDto,
                               BindingResult bindingResult,
                               Model model) {
        model.addAttribute("loginDto",loginDto);
        return "/member/login";
    }*/
    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("joinDto",new JoinDto());
        return "/member/join";
    }
    @GetMapping("/mypage")
    public String mypage(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        //model.addAttribute("joinDto",new JoinDto());
        log.info("{}",customUserDetails.getLoggedMember().toString());
        model.addAttribute("joinDto",customUserDetails.getLoggedMember());
        return "/member/mypage";
    }

    @GetMapping("/modify")
    public String modify() {
        return "/member/modify";
    }

    @PostMapping("/modify")
    public String modifyProcess(@ModelAttribute JoinDto joinDto, Model model,
                                RedirectAttributes redirectAttributes) {
        // 모달띄워서 바껴다고 알려주기...
        int result = memberService.updateMember(joinDto);
        if(result>0) {
            SecurityContextHolder.getContext().setAuthentication(null);
            ModalDto modalDto = ModalDto.builder()
                    .isState("success")
                    .msg("회원정보 수정되었습니다. 다시 로그인 해주세요.")
                    .build();
            redirectAttributes.addFlashAttribute("modalDto",modalDto);
            return "redirect:/";
        }
        log.info("joinDto==={}",joinDto.toString());
        model.addAttribute("error",true);
        model.addAttribute("exception","패스워드 확인해 주세요");
        return "/member/modify";
    }


    @GetMapping("/delete")
    public String delete() {
        return "/member/delete";
    }

    @PostMapping("/delete")
    //@ResponseBody
    public String deleteProcess(@ModelAttribute LoginDto loginDto, Model model) {
        log.info("=={},==={}",loginDto.getUserId(),loginDto.getPassword());
        int result = memberService.deleteMember(loginDto);
        if(result>0) {
            SecurityContextHolder.getContext().setAuthentication(null);
            return "redirect:/";
        }
        model.addAttribute("error",true);
        model.addAttribute("exception","아이디 패스워드 확인해 주세요");
        return "/member/delete";
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Map<String,String> deleteAjaxProcess(@ModelAttribute LoginDto loginDto, Model model) {
        int result = memberService.deleteMember(loginDto);
        Map<String,String> resultMap = new HashMap<>();
        if(result>0) {
            SecurityContextHolder.getContext().setAuthentication(null);
            resultMap.put("isState","ok");
            return resultMap;
        }
        resultMap.put("isState","fail");
        return resultMap;
    }

    @PostMapping("/join")
    public String joinProcess(@Valid @ModelAttribute JoinDto joinDto,
                               BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("joinDto",joinDto);
            return "/member/join";
        }
        memberService.insertMember(joinDto);
        return "redirect:/member/login";
    }
    @PutMapping("/modify")
    @ResponseBody
    public Map<String, String> modifyAjaxProcess(@ModelAttribute JoinDto joinDto,Model model) {
        log.info("joinDto==={}",joinDto.toString());
        int result = memberService.updateMember(joinDto);
        Map<String,String> resultMap = new HashMap<>();
        if(result>0) {
            SecurityContextHolder.getContext().setAuthentication(null);
            resultMap.put("isState","ok");
            return resultMap;
        }
        resultMap.put("isState","fail");
        return resultMap;
    }


}
