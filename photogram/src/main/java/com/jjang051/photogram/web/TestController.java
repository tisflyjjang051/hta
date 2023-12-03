package com.jjang051.photogram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/test/test")
    public String aaa() {
        return "/test/test";
    }
}
