package com.songpapeople.hashtagmap.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class WebRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @DisplayName("profile을 확인하는 api Test")
    @Test
    public void checkProfile () {
        String expectedProfile = "set1";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile("db");
        env.addActiveProfile(expectedProfile);

        WebRestController controller = new WebRestController(env);

        String profile = controller.getProfile();

        assertThat(profile).isEqualTo(expectedProfile);
    }
}
