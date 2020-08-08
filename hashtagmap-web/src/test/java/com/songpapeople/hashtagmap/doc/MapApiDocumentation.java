package com.songpapeople.hashtagmap.doc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.songpapeople.hashtagmap.doc.ApiDocumentUtils.getDocumentRequest;
import static com.songpapeople.hashtagmap.doc.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MapApiDocumentation extends ApiDocument {
    @DisplayName("findAllMarkers 문서화를 위한 테스트")
    @Test
    void findAllMarkers() throws Exception {
        given(instagramQueryService.findAllMarkers()).willReturn(markerResponses);
        mockMvc.perform(get("/markers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(getDocumentByFindAllMarkers());
    }

    private RestDocumentationResultHandler getDocumentByFindAllMarkers() {
        return document("maps/find-all-markers",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("data[0].placeName").type(JsonFieldType.STRING)
                                .description("The place name of the first data."),
                        fieldWithPath("data[0].kakaoId").type(JsonFieldType.STRING)
                                .description("The unique kakao place id of the first data."),
                        fieldWithPath("data[0].instagramId").type(JsonFieldType.NUMBER)
                                .description("The unique instagram id of the first data."),
                        fieldWithPath("data[0].hashtagCount").type(JsonFieldType.NUMBER)
                                .description("The instagram hashtag count of the first data."),
                        fieldWithPath("data[0].tagLevel").type(JsonFieldType.NUMBER)
                                .description("The tag level of the first data."),
                        fieldWithPath("data[0].latitude").type(JsonFieldType.STRING)
                                .description("The latitude of the first data."),
                        fieldWithPath("data[0].longitude").type(JsonFieldType.STRING)
                                .description("The longitude of the first data."),
                        fieldWithPath("code").type(JsonFieldType.NULL).description("The error code"),
                        fieldWithPath("message").type(JsonFieldType.NULL).description("The error message")
                )
        );
    }
}
