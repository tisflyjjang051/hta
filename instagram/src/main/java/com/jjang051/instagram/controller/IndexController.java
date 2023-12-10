package com.jjang051.instagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping({"/","/index"})
    public String index() {
        return "/index/index";
    }
}
