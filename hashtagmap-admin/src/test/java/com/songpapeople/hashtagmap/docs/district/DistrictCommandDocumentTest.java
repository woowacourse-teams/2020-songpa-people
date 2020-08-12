package com.songpapeople.hashtagmap.docs.district;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songpapeople.hashtagmap.district.api.DistrictCommandController;
import com.songpapeople.hashtagmap.district.service.DistrictCommandService;
import com.songpapeople.hashtagmap.district.service.dto.DistrictDeleteDto;
import com.songpapeople.hashtagmap.district.service.dto.DistrictSaveDto;
import com.songpapeople.hashtagmap.district.service.dto.DistrictUpdateDto;
import com.songpapeople.hashtagmap.docs.ApiDocument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import java.util.Arrays;

import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentRequest;
import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.NULL;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DistrictCommandController.class)
@ExtendWith({MockitoExtension.class})
public class DistrictCommandDocumentTest extends ApiDocument {
    private static final String BASE_URI = "/districts";

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DistrictCommandService districtCommandService;

    @DisplayName("전체 지역 정보 가져오기 문서화")
    @Test
    void getAllDistrict() throws Exception {
        doReturn(1L).when(districtCommandService).saveDistrict(any());

        DistrictSaveDto districtSaveDto = new DistrictSaveDto("서울 송파구");
        String content = objectMapper.writeValueAsString(districtSaveDto);
        this.mockMvc.perform(
                post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(saveDistrictDocs());
    }

    private RestDocumentationResultHandler saveDistrictDocs() {
        return document("district/saveDistrict",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("code").type(NULL).description("에러 코드"),
                        fieldWithPath("message").type(NULL).description("에러 메세지"),
                        fieldWithPath("data").type(NUMBER).description("저장된 지역정보의 ID")
                )
        );
    }

    @DisplayName("선택한 지역 지우기 문서화")
    @Test
    void deleteDistricts() throws Exception {
        //given
        doNothing().when(districtCommandService).deleteDistricts(any());

        DistrictDeleteDto districtDeleteDto = new DistrictDeleteDto(Arrays.asList(1L, 2L));
        String content = objectMapper.writeValueAsString(districtDeleteDto);

        this.mockMvc.perform(
                delete(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(emptyDocs("district/deleteDistricts"));
    }

    @DisplayName("선택한 지역 수정하기 문서화")
    @Test
    void updateDistricts() throws Exception {
        //given
        doNothing().when(districtCommandService).updateDistrict(any());

        DistrictUpdateDto districtUpdateDto = new DistrictUpdateDto(1L, "수정");
        String content = objectMapper.writeValueAsString(districtUpdateDto);

        this.mockMvc.perform(
                patch(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(emptyDocs("district/updateDistrict"));
    }

    private RestDocumentationResultHandler emptyDocs(String path) {
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
}
