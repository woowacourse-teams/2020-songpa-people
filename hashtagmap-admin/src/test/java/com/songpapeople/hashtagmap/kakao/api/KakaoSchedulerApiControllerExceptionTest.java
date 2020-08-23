package com.songpapeople.hashtagmap.kakao.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.AdminExceptionStatus;
import com.songpapeople.hashtagmap.exception.CommonExceptionStatus;
import com.songpapeople.hashtagmap.kakao.service.KakaoScheduleCommandService;
import com.songpapeople.hashtagmap.kakao.service.KakaoScheduleQueryService;
import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerException;
import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerExceptionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = KakaoSchedulerApiController.class)
@ExtendWith(MockitoExtension.class)
class KakaoSchedulerApiControllerExceptionTest {
    private static final String BASE_URI = "/kakao/scheduler";
    private static final String LOG = "LOG";

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private KakaoScheduleQueryService kakaoScheduleQueryService;

    @MockBean
    private KakaoScheduleCommandService kakaoScheduleCommandService;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @DisplayName("스케쥴러를 실행할 때 찾고자 하는 스케쥴러가 없는 경우 Exception")
    @Test
    void startCronNotFound() throws Exception {
        doThrow(new AdminException(AdminExceptionStatus.NOT_FOUND_SCHEDULER, LOG)).when(kakaoScheduleCommandService).startSchedule();

        //given
        MvcResult mvcResult = this.mockMvc.perform(post(BASE_URI + "/start"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        //when
        String contentAsString = mvcResult.getResponse().getContentAsString();
        CustomResponse<Void> customResponse = objectMapper.readValue(contentAsString, new TypeReference<CustomResponse<Void>>() {
        });

        //then
        assertThat(customResponse.getCode()).isEqualTo(AdminExceptionStatus.NOT_FOUND_SCHEDULER.getCode());
        assertThat(customResponse.getMessage()).isEqualTo(AdminExceptionStatus.NOT_FOUND_SCHEDULER.getMessage());
    }

    @DisplayName("스케쥴러가 이미 실행중일때 Exception")
    @Test
    void startCron() throws Exception {
        doThrow(new KakaoSchedulerException(KakaoSchedulerExceptionStatus.SCHEDULE_ALREADY_RUNNING, LOG)).when(kakaoScheduleCommandService).startSchedule();

        //given
        MvcResult mvcResult = this.mockMvc.perform(post(BASE_URI + "/start"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        //when
        String contentAsString = mvcResult.getResponse().getContentAsString();
        CustomResponse<Void> customResponse = objectMapper.readValue(contentAsString, new TypeReference<CustomResponse<Void>>() {
        });

        //then
        assertThat(customResponse.getCode()).isEqualTo(KakaoSchedulerExceptionStatus.SCHEDULE_ALREADY_RUNNING.getCode());
        assertThat(customResponse.getMessage()).isEqualTo(KakaoSchedulerExceptionStatus.SCHEDULE_ALREADY_RUNNING.getMessage());
    }

    @DisplayName("request body가 exception 발생한 경우 exception 발생")
    @Test
    void requestBodyValidException() throws Exception {
        //given
        doNothing().when(kakaoScheduleCommandService).toggleScheduleAutoRunnable(anyString());
        String emptyBody = "{}";

        //when
        MvcResult mvcResult = mockMvc.perform(patch(BASE_URI + "/auto/toggle")
                .content(emptyBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        CustomResponse<Void> customResponse = objectMapper.readValue(contentAsString, new TypeReference<CustomResponse<Void>>() {
        });

        //then
        assertThat(customResponse.getCode()).isEqualTo(CommonExceptionStatus.WRONG_ARGUMENT.getCode());
    }
}