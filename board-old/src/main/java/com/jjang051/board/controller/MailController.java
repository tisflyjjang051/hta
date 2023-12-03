package com.jjang051.board.controller;

import com.jjang051.board.code.ErrorCode;
import com.jjang051.board.dto.LoginDto;
import com.jjang051.board.dto.MailDto;
import com.jjang051.board.dto.UpdateDto;
import com.jjang051.board.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;
    @GetMapping("/mail")
    public String mail() {
        return "/mail/mail";
    }
    @PostMapping("/send")
    public String send(@ModelAttribute MailDto mailDto) {
        mailService.sendMail(mailDto);
        return "redirect:/";
    }

    @PostMapping("/confirm")
    @ResponseBody
    public Map<String, String> confirm(String mail) {
        String randomNum = mailService.sendAuthEmail(mail);
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("confirmNumber",randomNum);
        return resultMap;
        //{confirmNumber:23435}
    }

    @GetMapping("/find_password")
    public String findPassword() {
        // 이메일을 입력하면 비밀번호 만들어서  메일로 보내고 임시 번호 메일로 보냈습니다.
        // 임시비밀번호를 암호화해서 넣어두기...
        return "/mail/find_password";
    }

    @PostMapping("/find_password")
    public String findPasswordProcess(UpdateDto updateDto) {
        //비밀번호를 암호화해서 넣어둔다. update
        //int randomNum = mailService.sendAuthEmail(mail);  // 메일보내고 랜덤값 뱉어내기....
        int result = mailService.sendMailAndChangePassword(updateDto);
        return "redirect:/member/login";

    }

}
