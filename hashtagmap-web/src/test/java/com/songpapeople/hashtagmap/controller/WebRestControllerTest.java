package com.songpapeople.hashtagmap.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebRestControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void checkProfile() {
        String profile = this.restTemplate.getForObject("/profile", String.class);

        assertThat(profile).isEqualTo("local");
    }
}
