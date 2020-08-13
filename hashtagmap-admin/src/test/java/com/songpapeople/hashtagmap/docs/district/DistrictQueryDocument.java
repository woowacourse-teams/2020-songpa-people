package com.songpapeople.hashtagmap.docs.district;

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

public class DistrictQueryDocument {
    public static RestDocumentationResultHandler getAllDistrictDoc() {
        return document("district/getAllDistrict",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("code").type(NULL).description("에러 코드"),
                        fieldWithPath("message").type(NULL).description("에러 메세지"),
                        fieldWithPath("data[]").type(ARRAY).description("지역정보 리스트"),
                        fieldWithPath("data[0].districtId").type(NUMBER).description("District 아이디"),
                        fieldWithPath("data[0].districtName").type(STRING).description("지역 이름"),
                        fieldWithPath("data[0].createdTime").type(STRING).description("저장 시간"),
                        fieldWithPath("data[0].updatedTime").type(STRING).description("수정 시간"),
                        fieldWithPath("data[0].memberName").type(STRING).description("수정한 사용자")
                )
        );
    }

    public static RestDocumentationResultHandler getAllDistrictNameDocs() {
        return document("district/getAllDistrictName",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("code").type(NULL).description("에러 코드"),
                        fieldWithPath("message").type(NULL).description("에러 메세지"),
                        fieldWithPath("data[]").type(ARRAY).description("District 이름 List")
                )
        );
    }

    public static RestDocumentationResultHandler getAllZoneDocs() {
        return document("district/zone/getAllZone",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("code").type(NULL).description("에러 코드"),
                        fieldWithPath("message").type(NULL).description("에러 메세지"),
                        fieldWithPath("data[]").type(ARRAY).description("Zone 리스트"),
                        fieldWithPath("data[0].zoneId").type(NUMBER).description("Zone 아이디"),
                        fieldWithPath("data[0].districtId").type(NUMBER).description("District 아이디"),
                        fieldWithPath("data[0].districtName").type(STRING).description("District 이름"),
                        fieldWithPath("data[0].topLeftLatitude").type(STRING).description("좌 상단 위도(y)"),
                        fieldWithPath("data[0].topLeftLongitude").type(STRING).description("좌 상단 경도(x)"),
                        fieldWithPath("data[0].bottomRightLatitude").type(STRING).description("우 하단 위도(y)"),
                        fieldWithPath("data[0].bottomRightLongitude").type(STRING).description("우 하단 경도(y)")
                )
        );
    }
}
