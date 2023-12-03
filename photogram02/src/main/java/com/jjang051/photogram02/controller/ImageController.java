package com.jjang051.photogram02.controller;

import com.jjang051.photogram02.dto.CustomUserDetailsDto;
import com.jjang051.photogram02.dto.ImageUploadDto;
import com.jjang051.photogram02.handler.exception.CustomValidationException;
import com.jjang051.photogram02.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    @GetMapping({"/","/image/story"})
    public String story() {
        return "/image/story";
    }


    @GetMapping("/image/popular")
    public String popular() {
        return "/image/popular";
    }
    @GetMapping("/image/upload")
    public String upload() {
        return "/image/upload";
    }


    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal CustomUserDetailsDto customUserDetailsDto) {

        // 깍둑이
        if(imageUploadDto.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
        }

        imageService.upload(imageUploadDto, customUserDetailsDto);
        return "redirect:/user/"+customUserDetailsDto.getLoggedUser().getId();
    }
}
