package com.songpapeople.hashtagmap.doc;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.songpapeople.hashtagmap.doc.ApiDocumentUtils.getDocumentRequest;
import static com.songpapeople.hashtagmap.doc.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class MapApiDocumentation extends ApiDocument {
    protected RestDocumentationResultHandler getDocumentByFindAllMarkers() {
        return document("maps/find-all-markers",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("data[0].placeName").type(JsonFieldType.STRING)
                                .description("The place name of the first data."),
                        fieldWithPath("data[0].placeUrl").type(JsonFieldType.STRING)
                                .description("The Url of the place. "),
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
                        fieldWithPath("data[0].category").type(JsonFieldType.STRING)
                        .description("The category name which place belong to"),
                        fieldWithPath("code").type(JsonFieldType.NULL).description("The error code"),
                        fieldWithPath("message").type(JsonFieldType.NULL).description("The error message")
                )
        );
    }
}
