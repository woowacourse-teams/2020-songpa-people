package com.songpapeople.hashtagmap.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class RouterController implements ErrorController {
    @GetMapping({"/", "/error"})
    @ResponseStatus(HttpStatus.OK)
    public String index() {
        return "index";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
