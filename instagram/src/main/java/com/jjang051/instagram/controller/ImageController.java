package com.jjang051.instagram.controller;


import com.jjang051.instagram.dto.CustomUserDetails;
import com.jjang051.instagram.dto.ImageUploadDto;
import com.jjang051.instagram.exception.CustomValidationException;
import com.jjang051.instagram.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;
    @GetMapping({"/","/story"})
    public String story() {
        return "/image/story";
    }


    @GetMapping("/popular")
    public String popular() {
        return "/image/popular";
    }
    @GetMapping("/upload")
    public String upload() {
        return "/image/upload";
    }


    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal CustomUserDetails customUserDetails) throws CustomValidationException {

        // 깍둑이
        if(imageUploadDto.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
        }

        imageService.upload(imageUploadDto, customUserDetails);
        return "redirect:/member/mypage/"+customUserDetails.getLoggedMember().getId();
    }
}
