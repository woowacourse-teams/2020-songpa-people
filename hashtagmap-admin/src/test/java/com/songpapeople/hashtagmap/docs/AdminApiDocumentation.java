package com.songpapeople.hashtagmap.docs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songpapeople.hashtagmap.kakao.schedule.PeriodHistoryDto;
import com.songpapeople.hashtagmap.kakao.service.KakaoScheduleCommandService;
import com.songpapeople.hashtagmap.kakao.service.KakaoScheduleQueryService;
import com.songpapeople.hashtagmap.kakao.service.dto.KakaoScheduleToggleDto;
import com.songpapeople.hashtagmap.scheduler.InstagramScheduler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentRequest;
import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminApiDocumentation extends ApiDocument {
    private static String SCHEDULER_NAME = "KAKAO";

    @MockBean
    private KakaoScheduleCommandService kakaoScheduleCommandService;

    @MockBean
    private KakaoScheduleQueryService kakaoScheduleQueryService;

    @MockBean
    private InstagramScheduler instagramScheduler;

    @DisplayName("문서화 - Instagram Scheduler를 시작")
    @Test
    void updateDocs() throws Exception {
        doNothing().when(instagramScheduler).update();

        mockMvc.perform(put("/instagram-scheduler"))
                .andExpect(status().isOk())
                .andDo(document("instagram/scheduler/update",
                        getDocumentRequest(),
                        getDocumentResponse())
                );
    }

    @DisplayName("문서화 - Kakao Scheduler를 키고 끄는 toggle")
    @Test
    void toggleDocs() throws Exception {
        doNothing().when(kakaoScheduleCommandService).toggleSchedule(anyString());

        ObjectMapper objectMapper = new ObjectMapper();
        String requestDto = objectMapper.writeValueAsString(new KakaoScheduleToggleDto(SCHEDULER_NAME));

        mockMvc.perform(post("/kakao/scheduler/toggle")
                .content(requestDto)
                .header("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andDo(document("kakao/scheduler/toggle",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING)
                                        .description("스케줄러의 이름")
                        )
                ));
    }


    @DisplayName("문서화 - Kakao Scheduler 상태 조회")
    @Test
    void statusDocs() throws Exception {
        when(kakaoScheduleQueryService.getKakaoScheduleActiveStatus(anyString())).thenReturn(true);

        mockMvc.perform(get("/kakao/scheduler/status")
                .param("name", SCHEDULER_NAME))
                .andExpect(status().isOk())
                .andDo(document("kakao/scheduler/status",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("name").description("스케줄러의 이름")
                        ),
                        responseFields(
                                fieldWithPath("data").type(JsonFieldType.BOOLEAN)
                                        .description("스케줄러의 상태 (지정된 주기가 되면 데이터를 수집하는 상태)"),
                                fieldWithPath("code").type(JsonFieldType.NULL).description("에러 코드"),
                                fieldWithPath("message").type(JsonFieldType.NULL).description("에러 메세지")
                        ))
                );
    }

    @DisplayName("문서화 - Kakao Scheduler 주기 변경")
    @Test
    void changeSchedulePeriodDocs() throws Exception {
        doNothing().when(instagramScheduler).update();

        mockMvc.perform(put("/kakao/scheduler/period")
                .content("* 0/5 * * * ?")
                .header("Content-Type", "application/json"))
                .andDo(MockMvcResultHandlers.print()) //
                .andExpect(status().isOk())
                .andDo(document("kakao/scheduler/period/put",
                        getDocumentRequest(),
                        getDocumentResponse()
                ));
    }

    @DisplayName("문서화 - Kakao Scheduler 주기 조회")
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
                .andDo(document("kakao/scheduler/period/get",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("data[0].id").type(JsonFieldType.NUMBER)
                                        .description("주기 기록의 ID"),
                                fieldWithPath("data[0].expression").type(JsonFieldType.STRING)
                                        .description("정규식"),
                                fieldWithPath("data[0].member").type(JsonFieldType.STRING)
                                        .description("주기를 변경한 멤버"),
                                fieldWithPath("data[0].changedDate").type(JsonFieldType.STRING)
                                        .description("변경한 날짜"),
                                fieldWithPath("code").type(JsonFieldType.NULL).description("에러 코드"),
                                fieldWithPath("message").type(JsonFieldType.NULL).description("에러 메세지")
                        ))
                );
    }
}
