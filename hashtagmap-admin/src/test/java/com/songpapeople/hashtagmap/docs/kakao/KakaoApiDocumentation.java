package com.songpapeople.hashtagmap.docs.kakao;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentRequest;
import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;

public class KakaoApiDocumentation {
    public static RestDocumentationResultHandler getDocumentByToggle() {
        return document("kakao/scheduler/toggle",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING)
                                .description("스케줄러의 이름")
                )
        );
    }

    public static RestDocumentationResultHandler getDocumentByGetStatus() {
        return document("kakao/scheduler/status",
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
                ));
    }

    public static RestDocumentationResultHandler getDocumentByChangePeriod() {
        return document("kakao/scheduler/period/put",
                getDocumentRequest(),
                getDocumentResponse()
        );
    }

    public static RestDocumentationResultHandler getDocumentByShowPeriodHistory() {
        return document("kakao/scheduler/period/get",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("data[0].id").type(JsonFieldType.NUMBER).description("데이터 베이스 Id"),
                        fieldWithPath("data[0].expression").type(JsonFieldType.STRING).description("변경된 주기 (cron)"),
                        fieldWithPath("data[0].member").type(JsonFieldType.STRING).description("수정한 사람"),
                        fieldWithPath("data[0].changedDate").type(JsonFieldType.STRING).description("수정한 날짜"),
                        fieldWithPath("code").type(JsonFieldType.NULL).description("에러 코드"),
                        fieldWithPath("message").type(JsonFieldType.NULL).description("에러 메세지")
                )
        );
    }
}
