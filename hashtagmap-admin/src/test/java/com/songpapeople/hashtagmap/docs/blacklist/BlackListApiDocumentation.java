package com.songpapeople.hashtagmap.docs.blacklist;

import com.songpapeople.hashtagmap.docs.ApiDocument;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentRequest;
import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.NULL;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class BlackListApiDocumentation extends ApiDocument {
    protected RestDocumentationResultHandler getDocumentByGetSubBlackList() {
        return document("blacklist/sub",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("code").type(NULL).description("에러 코드"),
                        fieldWithPath("message").type(NULL).description("에러 메세지"),
                        fieldWithPath("data[]").type(ARRAY).description("블랙리스트 후보들"),
                        fieldWithPath("data[0].placeName").type(STRING).description("블랙리스트 장소 이름"),
                        fieldWithPath("data[0].hashtagCount").type(NUMBER).description("블랙리스트의 해시태그수"),
                        fieldWithPath("data[0].roadAddressName").type(STRING).description("블랙리스트의 kakao place상의 주소")
                ));
    }
}
