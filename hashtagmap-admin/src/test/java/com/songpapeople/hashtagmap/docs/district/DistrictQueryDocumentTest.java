package com.songpapeople.hashtagmap.docs.district;

import com.songpapeople.hashtagmap.district.api.DistrictQueryController;
import com.songpapeople.hashtagmap.district.service.DistrictQueryService;
import com.songpapeople.hashtagmap.district.service.dto.DistrictDto;
import com.songpapeople.hashtagmap.docs.ApiDocument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentRequest;
import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.Mockito.doReturn;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.NULL;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DistrictQueryController.class)
@ExtendWith({MockitoExtension.class})
public class DistrictQueryDocumentTest extends ApiDocument {
    private static final String BASE_URI = "/districts";

    @MockBean
    private DistrictQueryService districtQueryService;

    @DisplayName("전체 지역 정보 가져오기 문서화")
    @Test
    void getAllDistrict() throws Exception {
        List<DistrictDto> districtDtos = Arrays.asList(new DistrictDto(1L, "서울 송파구", LocalDateTime.now(), LocalDateTime.now(), "어드민"));
        doReturn(districtDtos).when(districtQueryService).getAllDistrict();

        this.mockMvc.perform(
                get(BASE_URI)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(getAllDistrictDoc());
    }

    private RestDocumentationResultHandler getAllDistrictDoc() {
        return document("district/getAllDistrict",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("code").type(NULL).description("에러 코드"),
                        fieldWithPath("message").type(NULL).description("에러 메세지"),
                        fieldWithPath("data[]").type(ARRAY).description("지역정보 리스트"),
                        fieldWithPath("data[0].districtId").type(NUMBER).description("지역 아이디"),
                        fieldWithPath("data[0].districtName").type(STRING).description("지역 이름"),
                        fieldWithPath("data[0].createdTime").type(STRING).description("저장 시간"),
                        fieldWithPath("data[0].updatedTime").type(STRING).description("수정 시간"),
                        fieldWithPath("data[0].memberName").type(STRING).description("수정한 사용자")
                )
        );
    }
}
