package com.songpapeople.hashtagmap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping
    public String index() {
        return "/index.html";
    }

    @GetMapping("kakao-scheduler")
    public String kakaoScheduler() {
        return "/index.html";
    }
}
