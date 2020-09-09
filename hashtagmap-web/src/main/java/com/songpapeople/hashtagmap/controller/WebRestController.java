package com.songpapeople.hashtagmap.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@AllArgsConstructor
public class WebRestController {
    private Environment environment;

    @GetMapping("/profile")
    public String getProfile() {
        return Arrays.stream(environment.getActiveProfiles())
                .filter(profile -> !profile.equals("db"))
                .findFirst()
                .orElse("");
    }
}
