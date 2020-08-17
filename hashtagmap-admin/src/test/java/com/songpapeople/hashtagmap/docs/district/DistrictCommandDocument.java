package com.songpapeople.hashtagmap.docs.district;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentRequest;
import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.NULL;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class DistrictCommandDocument {
    public static RestDocumentationResultHandler saveDistrictDocs() {
        return document("district/saveDistrict",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("code").type(NULL).description("에러 코드"),
                        fieldWithPath("message").type(NULL).description("에러 메세지"),
                        fieldWithPath("data").type(NUMBER).description("저장된 District의 ID")
                )
        );
    }

    public static RestDocumentationResultHandler emptyDocs(String path) {
        return document(path,
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("code").type(NULL).description("에러 코드"),
                        fieldWithPath("message").type(NULL).description("에러 메세지"),
                        fieldWithPath("data").type(NULL).description("반환값 없음")
                )
        );
    }

    public static RestDocumentationResultHandler saveZoneDocs() {
        return document("district/zone/saveZone",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("code").type(NULL).description("에러 코드"),
                        fieldWithPath("message").type(NULL).description("에러 메세지"),
                        fieldWithPath("data").type(NUMBER).description("저장된 Zone의 ID")
                )
        );
    }

    public static RestDocumentationResultHandler updateZoneDocs() {
        return document("district/zone/updateZone",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("code").type(NULL).description("에러 코드"),
                        fieldWithPath("message").type(NULL).description("에러 메세지"),
                        fieldWithPath("data").type(NULL).description("반환값 없음")
                )
        );
    }

    public static RestDocumentationResultHandler deleteZonesDocs() {
        return document("district/zone/deleteZones",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("code").type(NULL).description("에러 코드"),
                        fieldWithPath("message").type(NULL).description("에러 메세지"),
                        fieldWithPath("data").type(NULL).description("반환값 없음")
                )
        );
    }
}
