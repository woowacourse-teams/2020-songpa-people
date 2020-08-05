package com.songpapeople.hashtagmap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping(value = {"/", "/kakao-scheduler"})
    public String index() {
        return "/index.html";
    }
}
