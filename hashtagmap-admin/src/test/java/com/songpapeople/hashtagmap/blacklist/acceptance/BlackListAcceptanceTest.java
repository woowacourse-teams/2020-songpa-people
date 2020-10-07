package com.songpapeople.hashtagmap.blacklist.acceptance;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
import com.songpapeople.hashtagmap.docs.ApiDocument;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlackListAcceptanceTest extends ApiDocument {
    @LocalServerPort
    int port;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private InstagramPostRepository instagramPostRepository;

    @Autowired
    private BlackListRepository blackListRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("잘못된 검색어로 검색된 가게를 블랙리스트 등록후 삭제한다")
    @Test
    void deleteInstagramAndPost() throws Exception {
        Place place = Place.builder()
                .placeName("place")
                .location(new Location(new Point("40", "130"), "address"))
                .kakaoId("15124")
                .build();
        Instagram instagram = Instagram.builder()
                .place(place)
                .hashtagCount(100L)
                .hashtagName(place.getPlaceName())
                .build();
        placeRepository.save(place);
        instagramRepository.save(instagram);
        instagramPostRepository.saveAll(createInstagramPosts(instagram));

        Map<String, String> params = new HashMap<>();
        params.put("kakaoId", place.getKakaoId());
        params.put("replaceName", "");
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when()
                .delete("/blacklist/instagram")
                .then()
                .log().all();

        BlackList blackList = blackListRepository.findByKakaoId(place.getKakaoId()).get();
        Optional<Instagram> instagramFindById = instagramRepository.findById(instagram.getId());
        List<InstagramPost> instagramPost = instagramPostRepository.findAllByInstagramId(instagram.getId());
        assertAll(
                () -> assertThat(blackList.getIsSkipPlace()).isTrue(),
                () -> assertThat(instagramFindById.isPresent()).isFalse(),
                () -> assertThat(instagramPost).hasSize(0)
        );
    }

    private List<InstagramPost> createInstagramPosts(Instagram instagram) {
        return IntStream.rangeClosed(1, 9)
                .boxed()
                .map(number -> InstagramPost.builder()
                        .instagram(instagram)
                        .postUrl(String.valueOf(number))
                        .imageUrl(String.valueOf(number))
                        .build())
                .collect(Collectors.toList());
    }
}
