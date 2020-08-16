package com.songpapeople.hashtagmap.docs.taglevel;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentRequest;
import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class TagLevelDocumentation {
    public static RestDocumentationResultHandler getBasicDocument(String identifier) {
        return document(identifier,
                getDocumentRequest(),
                getDocumentResponse()
        );
    }

    public static RestDocumentationResultHandler getDocumentByGetTagLevels() {
        return document("tag-levels/findAll",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("data[0].tagLevel").type(JsonFieldType.NUMBER).description("태그 레벨 번호"),
                        fieldWithPath("data[0].minHashtagCount").type(JsonFieldType.NUMBER)
                                .description("해당 태그 레벨의 최소 hashtag 개수"),
                        fieldWithPath("data[0].maxHashtagCount").type(JsonFieldType.NUMBER)
                                .description("해당 태그 레벨의 최대 hashtag 개수"),
                        fieldWithPath("code").type(JsonFieldType.NULL).description("에러 코드"),
                        fieldWithPath("message").type(JsonFieldType.NULL).description("에러 메세지")
                )
        );
    }
}
