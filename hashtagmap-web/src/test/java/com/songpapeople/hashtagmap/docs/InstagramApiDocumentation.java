package com.songpapeople.hashtagmap.docs;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentRequest;
import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class InstagramApiDocumentation extends ApiDocument {
    protected RestDocumentationResultHandler getDocumentByGetInstagramPost() {
        return document("instagram/get-post",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("data[0].id").type(JsonFieldType.NUMBER)
                                .description("instagramPost의 id"),
                        fieldWithPath("data[0].imageUrl").type(JsonFieldType.STRING)
                                .description("modal에서 사용하는 이미지의 url"),
                        fieldWithPath("data[0].postUrl").type(JsonFieldType.STRING)
                                .description("modal에서 링크시켜줄 인스타그램 게시물의 url"),
                        fieldWithPath("code").type(JsonFieldType.NULL).description("The error code"),
                        fieldWithPath("message").type(JsonFieldType.NULL).description("The error message")
                )
        );
    }
}
