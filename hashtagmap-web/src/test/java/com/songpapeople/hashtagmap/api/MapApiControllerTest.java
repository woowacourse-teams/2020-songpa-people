package com.songpapeople.hashtagmap.api;


import com.songpapeople.hashtagmap.doc.ApiDocument;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MapApiControllerTest extends ApiDocument {

    @DisplayName("마커를 만드는데 필요한 정보를 잘 받아오는지 테스트")
    @Test
    public void findAllMarkers() throws Exception {
        given(instagramQueryService.findAllMarkers()).willReturn(markerResponses);
        mockMvc.perform(get("/markers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data[0].placeName", Matchers.is("스타벅스")))
                .andExpect(jsonPath("$.data[0].kakaoId", Matchers.is("777")))
                .andExpect(jsonPath("$.data[0].latitude", Matchers.is("1")))
                .andExpect(jsonPath("$.data[0].longitude", Matchers.is("2")))
                .andExpect(jsonPath("$.data[0].instagramId", Matchers.is(1)))
                .andExpect(jsonPath("$.data[0].hashtagCount", Matchers.is(10000)))
                .andDo(print());
    }
}
