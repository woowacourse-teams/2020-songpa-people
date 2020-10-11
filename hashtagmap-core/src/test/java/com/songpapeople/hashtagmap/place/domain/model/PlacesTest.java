package com.songpapeople.hashtagmap.place.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlacesTest {

    @DisplayName("겹치지 않는 kakao id 가 다르면 컬렉션에 추가한다.")
    @Test
    void insertPlaces() {
        //given
        Location location = new Location(new Point("34", "124"), "도로명주소");
        Place kakao = new Place(null, "1", Category.CAFE, location, "카카오 카페", "kakao cafe");
        Place woowa = new Place(null, "2", Category.CAFE, location, "우형 카페", "woowa cafe");

        Places places = new Places(Collections.singletonList(kakao));

        //when
        places.insertPlaces(Arrays.asList(kakao, woowa));

        //then
        List<Place> afterInsert = places.get();
        assertThat(afterInsert).hasSize(2);
        assertThat(afterInsert.get(1).getPlaceName()).isEqualTo("우형 카페");
    }

    @DisplayName("kakao id 가 같은 place의 내용을 전부 교체한다.")
    @Test
    void updatePlaces() {
        //given
        Location location = new Location(new Point("34", "124"), "도로명주소");
        Place kakao = new Place(null, "1", Category.CAFE, location, "카카오 카페", "kakao cafe");
        Place woowa = new Place(null, "1", Category.CAFE, location, "우형 카페", "woowa cafe");

        Places places = new Places(Collections.singletonList(kakao));

        //when
        places.updatePlaces(Collections.singletonList(woowa));

        //then
        assertThat(places.get().get(0).getPlaceName()).isEqualTo("우형 카페");
    }

}