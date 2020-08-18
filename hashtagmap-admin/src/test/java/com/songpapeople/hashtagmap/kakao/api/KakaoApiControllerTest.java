package com.songpapeople.hashtagmap.kakao.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songpapeople.hashtagmap.kakao.service.KakaoScheduleCommandService;
import com.songpapeople.hashtagmap.kakao.service.KakaoScheduleQueryService;
import com.songpapeople.hashtagmap.kakao.service.dto.KakaoScheduleToggleDto;
import com.songpapeople.hashtagmap.kakao.service.dto.PeriodHistoryDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.songpapeople.hashtagmap.docs.kakao.KakaoApiDocumentation.getDocumentByChangePeriod;
import static com.songpapeople.hashtagmap.docs.kakao.KakaoApiDocumentation.getDocumentByGetStatus;
import static com.songpapeople.hashtagmap.docs.kakao.KakaoApiDocumentation.getDocumentByShowPeriodHistory;
import static com.songpapeople.hashtagmap.docs.kakao.KakaoApiDocumentation.getDocumentByToggle;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(controllers = KakaoSchedulerApiController.class)
@AutoConfigureMockMvc
public class KakaoApiControllerTest {
    private static String SCHEDULER_NAME = "KAKAO";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KakaoScheduleCommandService kakaoScheduleCommandService;

    @MockBean
    private KakaoScheduleQueryService kakaoScheduleQueryService;

    @BeforeEach
    private void setUp(WebApplicationContext webApplicationContext,
                       RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @DisplayName("Kakao Scheduler를 키고 끄는 toggle")
    @Test
    void toggleTest() throws Exception {
        doNothing().when(kakaoScheduleCommandService).toggleSchedule(anyString());

        ObjectMapper objectMapper = new ObjectMapper();
        String requestDto = objectMapper.writeValueAsString(new KakaoScheduleToggleDto(SCHEDULER_NAME));

        mockMvc.perform(post("/kakao/scheduler/toggle")
                .content(requestDto)
                .header("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andDo(getDocumentByToggle());
    }

    @DisplayName("Kakao Scheduler 상태 조회")
    @Test
    void getStatusTest() throws Exception {
        when(kakaoScheduleQueryService.getKakaoScheduleActiveStatus(anyString())).thenReturn(true);

        mockMvc.perform(get("/kakao/scheduler/status")
                .param("name", SCHEDULER_NAME))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", Matchers.is(true)))
                .andDo(getDocumentByGetStatus());
    }

    @DisplayName("Kakao Scheduler 주기 변경")
    @Test
    void changeSchedulePeriodDocs() throws Exception {
        doNothing().when(kakaoScheduleCommandService).changeSchedulePeriod(anyString());

        mockMvc.perform(MockMvcRequestBuilders.put("/kakao/scheduler/period")
                .content("* 0/5 * * * ?")
                .header("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andDo(getDocumentByChangePeriod());
    }


    @DisplayName("Kakao Scheduler 주기 조회")
    @Test
    void showPeriodHistoryDocs() throws Exception {
        List<PeriodHistoryDto> periodHistoryDtos = Arrays.asList(
                new PeriodHistoryDto(1L, "0 0/5 * * * ?", "TOMMY", new Date()),
                new PeriodHistoryDto(2L, "0 0/5 * * * *", "DUNDUNG", new Date())
        );
        when(kakaoScheduleQueryService.showPeriodHistory()).thenReturn(periodHistoryDtos);

        mockMvc.perform(get("/kakao/scheduler/period")
                .header("Accept-Type", "application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id", Matchers.instanceOf(Integer.class)))
                .andExpect(jsonPath("$.data[0].expression", Matchers.instanceOf(String.class)))
                .andExpect(jsonPath("$.data[0].member", Matchers.instanceOf(String.class)))
                .andExpect(jsonPath("$.data[0].changedDate", Matchers.instanceOf(String.class)))
                .andDo(getDocumentByShowPeriodHistory());
    }
}
