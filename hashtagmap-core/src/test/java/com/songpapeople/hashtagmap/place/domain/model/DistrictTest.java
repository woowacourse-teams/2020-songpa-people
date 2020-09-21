package com.songpapeople.hashtagmap.place.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DistrictTest {

    @DisplayName("District 변경 테스트")
    @Test
    void update() {
        District district = new District("서울시 송파구");
        String replace = "서울시 강남구";

        district.update(replace);

        assertThat(district.getDistrictName()).isEqualTo(replace);
    }
}