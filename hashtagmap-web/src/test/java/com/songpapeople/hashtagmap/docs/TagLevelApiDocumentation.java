package com.songpapeople.hashtagmap.docs;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentRequest;
import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class TagLevelApiDocumentation extends ApiDocument {
    protected RestDocumentationResultHandler getDocumentByFindTagLevels() {
        return document("tag-levels/find-all",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("data[0].minHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The minimum hashtag count of tag level one"),
                        fieldWithPath("data[0].maxHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The maximum hashtag count of tag level one"),
                        fieldWithPath("data[0].minHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The minimum hashtag count of tag level two"),
                        fieldWithPath("data[0].maxHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The maximum hashtag count of tag level two"),
                        fieldWithPath("data[0].minHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The minimum hashtag count of tag level three"),
                        fieldWithPath("data[0].maxHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The maximum hashtag count of tag level three"),
                        fieldWithPath("data[0].minHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The minimum hashtag count of tag level four"),
                        fieldWithPath("data[0].maxHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The maximum hashtag count of tag level four"),
                        fieldWithPath("data[0].minHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The minimum hashtag count of tag level five"),
                        fieldWithPath("data[0].maxHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The maximum hashtag count of tag level five"),
                        fieldWithPath("code").type(JsonFieldType.NULL).description("The error code"),
                        fieldWithPath("message").type(JsonFieldType.NULL).description("The error message")
                )
        );
    }
}
