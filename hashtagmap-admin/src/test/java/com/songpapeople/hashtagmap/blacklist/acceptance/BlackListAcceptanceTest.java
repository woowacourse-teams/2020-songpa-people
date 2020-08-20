package com.songpapeople.hashtagmap.blacklist.acceptance;

import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListAddResponse;
import com.songpapeople.hashtagmap.docs.ApiDocument;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.scheduler.InstagramScheduleService;
import io.restassured.RestAssured;
import io.restassured.mapper.TypeRef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlackListAcceptanceTest extends ApiDocument {
    @LocalServerPort
    int port;

    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private BlackListRepository blackListRepository;

    @MockBean
    private InstagramScheduleService instagramScheduleService;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("블랙리스트를 추가하고, Instagram을 업데이트한다.")
    @Test
    void addBlackList() throws Exception {
        Place place = Place.builder()
                .placeName("place")
                .kakaoId("1")
                .category(Category.RESTAURANT)
                .placeUrl("placeUrl")
                .location(new Location(new Point("40", "130"), "address"))
                .build();
        Instagram instagram = Instagram.builder()
                .place(place)
                .hashtagName("oldName")
                .hashtagCount(99999L)
                .build();
        placeRepository.save(place);
        instagramRepository.save(instagram);

        when(instagramScheduleService.findHashtagCount(any())).thenReturn(1000L);

        Map<String, String> params = new HashMap<>();
        params.put("placeId", String.valueOf(place.getId()));
        params.put("replaceName", "newName");
        CustomResponse<BlackListAddResponse> response = RestAssured.given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/blacklist")
                .then()
                .log().all()
                .extract().as(new TypeRef<CustomResponse<BlackListAddResponse>>() {
                });

        assertAll(
                () -> assertThat(blackListRepository.findAll().size()).isNotZero(),
                () -> assertThat(response.getData().getPlaceId()).isEqualTo(place.getId()),
                () -> assertThat(response.getData().getReplaceName()).isEqualTo("newName"),
                () -> assertThat(response.getData().getHashtagCount()).isEqualTo(1000L)
        );
    }
}
