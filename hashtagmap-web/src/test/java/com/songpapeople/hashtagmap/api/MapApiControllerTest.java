package com.songpapeople.hashtagmap.api;


import com.songpapeople.hashtagmap.doc.MapApiDocumentation;
import com.songpapeople.hashtagmap.dto.MarkerResponse;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.service.InstagramQueryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
public class MapApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InstagramQueryService instagramQueryService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new ShallowEtagHeaderFilter())
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    public void findAllMarkers() throws Exception {
        given(instagramQueryService.findAllMarkers()).willReturn(Arrays.asList(
                MarkerResponse.from(Instagram.builder()
                        .place(Place.builder()
                                .placeName("스타벅스")
                                .kakaoId("777")
                                .location(new Location(new Point("1", "2"), null))
                                .build())
                        .id(1L)
                        .hashtagCount(10000L)
                        .build())
                )
        );
        mockMvc.perform(get("/maps/markers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data[0].placeName", Matchers.is("스타벅스")))
                .andExpect(jsonPath("$.data[0].kakaoId", Matchers.is("777")))
                .andExpect(jsonPath("$.data[0].latitude", Matchers.is("1")))
                .andExpect(jsonPath("$.data[0].longitude", Matchers.is("2")))
                .andExpect(jsonPath("$.data[0].instagramId", Matchers.is(1)))
                .andExpect(jsonPath("$.data[0].hashtagCount", Matchers.is(10000)))
                .andDo(print())
                .andDo(MapApiDocumentation.findAllMarkers());
    }
}
