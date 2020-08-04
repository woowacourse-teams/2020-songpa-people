package com.songpapeople.hashtagmap.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = KakaoScheduleController.class)
class KakaoScheduleControllerTest {
    @Autowired
    MockMvc mvc;

    @DisplayName("카카오 스케줄러 실행")
    @Test
    public void changePeriod() throws Exception {
        mvc.perform(post("/kakao-scheduler/change-period")
                .content("0 0/5 * * * ?"))
                .andExpect(status().isNoContent());
    }

    @DisplayName("(예외) 기간(정규식)을 입력하지 않았을 때")
    @ParameterizedTest
    @EmptySource
    public void changePeriodException(String expression) throws Exception {
        mvc.perform(post("/kakao-scheduler/change-period")
                .content(expression))
                .andExpect(status().isBadRequest());
    }

}