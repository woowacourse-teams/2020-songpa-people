package com.songpapeople.hashtagmap.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouterController implements ErrorController {

    @GetMapping(value = {"/",
            "/kakao-scheduler",
            "/instagram-scheduler",
            "/district-manage",
            "/tag-level",
            "/blacklist-manager",
            "/error"})
    public String index() {
        return "index";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
