package com.songpapeople.hashtagmap.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @DisplayName("profile을 확인하는 api Test")
    @Test
    public void checkProfile () {
        //when
        String profile = this.restTemplate.getForObject("/profile", String.class);

        //then
        assertThat(profile).isEqualTo("set1");
    }
}
