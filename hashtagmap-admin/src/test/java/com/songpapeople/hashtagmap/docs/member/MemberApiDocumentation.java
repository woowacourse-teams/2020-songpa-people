package com.songpapeople.hashtagmap.docs.member;

import com.songpapeople.hashtagmap.docs.ApiDocument;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentRequest;
import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

public class MemberApiDocumentation extends ApiDocument {
    protected RestDocumentationResultHandler getDocumentByAdminMemberLogin() {
        return document("member/login",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("nickName").type(JsonFieldType.STRING)
                                .description("유저 아이디"),
                        fieldWithPath("password").type(JsonFieldType.STRING)
                                .description("유저 비밀번호"))
        );
    }
}
