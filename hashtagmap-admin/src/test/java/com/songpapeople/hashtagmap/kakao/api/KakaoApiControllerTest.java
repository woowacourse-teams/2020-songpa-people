package com.songpapeople.hashtagmap.kakao.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songpapeople.hashtagmap.docs.kakao.KakaoApiDocumentation;
import com.songpapeople.hashtagmap.kakao.service.KakaoScheduleCommandService;
import com.songpapeople.hashtagmap.kakao.service.KakaoScheduleQueryService;
import com.songpapeople.hashtagmap.kakao.service.dto.KakaoScheduleToggleDto;
import com.songpapeople.hashtagmap.kakao.service.dto.PeriodHistoryDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = KakaoSchedulerApiController.class)
public class KakaoApiControllerTest extends KakaoApiDocumentation {
    private static final String SCHEDULER_NAME = "KAKAO";
    private static final String KAKAO = "KAKAO";

    @MockBean
    private KakaoScheduleCommandService kakaoScheduleCommandService;

    @MockBean
    private KakaoScheduleQueryService kakaoScheduleQueryService;

    @DisplayName("Kakao Scheduler를 실행한다.")
    @Test
    void startTest() throws Exception {
        doNothing().when(kakaoScheduleCommandService).startSchedule();

        mockMvc.perform(post("/kakao/scheduler/start"))
                .andExpect(status().isOk())
                .andDo(getDocumentByStartAndStop("start"));
    }

    @DisplayName("Kakao Scheduler를 정지한다.")
    @Test
    void stopTest() throws Exception {
        doNothing().when(kakaoScheduleCommandService).stopSchedule();

        mockMvc.perform(post("/kakao/scheduler/stop"))
                .andExpect(status().isOk())
                .andDo(getDocumentByStartAndStop("stop"));
    }


    @DisplayName("Kakao Scheduler 상태 조회")
    @Test
    void getStatusTest() throws Exception {
        when(kakaoScheduleQueryService.getKakaoScheduleActiveStatus()).thenReturn(true);

        mockMvc.perform(get("/kakao/scheduler")
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

    @DisplayName("Kakao Scheduler 자동 실행 상태 변경")
    @Test
    void toggleAutoRunnable() throws Exception {
        doNothing().when(kakaoScheduleCommandService).toggleScheduleAutoRunnable(KAKAO);

        KakaoScheduleToggleDto kakaoScheduleToggleDto = new KakaoScheduleToggleDto(KAKAO);
        String content = new ObjectMapper().writeValueAsString(kakaoScheduleToggleDto);

        mockMvc.perform(patch("/kakao/scheduler/auto/toggle")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        )
                .andExpect(status().isOk())
                .andDo(getDocumentByChangeAutoRunnable());
    }

    @DisplayName("Kakao Scheduler 자동 실행 상태 조회")
    @Test
    void getKakaoAutoRunnable() throws Exception {
        when(kakaoScheduleQueryService.getKakaoScheduleAutoRunnable(anyString())).thenReturn(true);

        mockMvc.perform(get("/kakao/scheduler/auto")
                .queryParam("name", KAKAO)
        )
                .andExpect(status().isOk())
                .andDo(getDocumentByGetAutoRunnable());
    }
}
