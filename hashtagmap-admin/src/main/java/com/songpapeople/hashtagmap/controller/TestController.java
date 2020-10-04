package com.songpapeople.hashtagmap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test/{errorCode}")
    public void test(@PathVariable("errorCode") String code) {
        throw new RuntimeException(code);
    }

    @PostMapping("/test/post")
    public void testpost() {
        throw new RuntimeException("post exception test");
    }
}
