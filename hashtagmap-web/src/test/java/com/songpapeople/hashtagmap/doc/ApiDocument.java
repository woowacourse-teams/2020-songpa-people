package com.songpapeople.hashtagmap.doc;

import com.songpapeople.hashtagmap.dto.MarkerResponse;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.service.InstagramQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
public class ApiDocument {
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected InstagramQueryService instagramQueryService;

    protected List<MarkerResponse> markerResponses;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
        this.markerResponses = Arrays.asList(
                MarkerResponse.from(Instagram.builder()
                        .place(Place.builder()
                                .placeName("스타벅스")
                                .kakaoId("777")
                                .location(new Location(new Point("1", "2"), null))
                                .build())
                        .id(1L)
                        .hashtagCount(10000L)
                        .build()
                )
        );
    }
}
