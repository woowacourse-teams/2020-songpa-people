package com.songpapeople.hashtagmap.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.songpapeople.hashtagmap.exception.CommonExceptionStatus;
import com.songpapeople.hashtagmap.response.CustomResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminExceptionControllerAdviceTest {

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @DisplayName("Http Method not allowed test")
    @Test
    void test() throws Exception {
        //given
        MvcResult mvcResult = mockMvc.perform(post("/"))
                .andExpect(status().isBadRequest())
                .andReturn();

        //when
        String contentAsString = mvcResult.getResponse().getContentAsString();
        CustomResponse<Void> response = objectMapper.readValue(contentAsString, new TypeReference<CustomResponse<Void>>() {
        });

        //then
        assertThat(response.getCode()).isEqualTo(CommonExceptionStatus.REQUEST_NOT_ALLOWED.getCode());
    }
}
