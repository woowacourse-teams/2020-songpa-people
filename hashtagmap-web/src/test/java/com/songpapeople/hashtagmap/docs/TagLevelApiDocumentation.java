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
                        fieldWithPath("data[0].level").type(JsonFieldType.NUMBER)
                                .description("First tag level."),
                        fieldWithPath("data[0].minHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The minimum hashtag count of tag level one."),
                        fieldWithPath("data[0].maxHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The maximum hashtag count of tag level one."),
                        fieldWithPath("data[1].level").type(JsonFieldType.NUMBER)
                                .description("Second tag level."),
                        fieldWithPath("data[1].minHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The minimum hashtag count of tag level two."),
                        fieldWithPath("data[1].maxHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The maximum hashtag count of tag level two."),
                        fieldWithPath("data[2].level").type(JsonFieldType.NUMBER)
                                .description("Third tag level."),
                        fieldWithPath("data[2].minHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The minimum hashtag count of tag level three."),
                        fieldWithPath("data[2].maxHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The maximum hashtag count of tag level three."),
                        fieldWithPath("data[3].level").type(JsonFieldType.NUMBER)
                                .description("Fourth tag level."),
                        fieldWithPath("data[3].minHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The minimum hashtag count of tag level four."),
                        fieldWithPath("data[3].maxHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The maximum hashtag count of tag level four."),
                        fieldWithPath("data[4].level").type(JsonFieldType.NUMBER)
                                .description("Fifth tag level."),
                        fieldWithPath("data[4].minHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The minimum hashtag count of tag level five."),
                        fieldWithPath("data[4].maxHashtagCount").type(JsonFieldType.NUMBER)
                                .description("The maximum hashtag count of tag level five."),
                        fieldWithPath("code").type(JsonFieldType.NULL).description("The error code."),
                        fieldWithPath("message").type(JsonFieldType.NULL).description("The error message.")
                )
        );
    }
}
