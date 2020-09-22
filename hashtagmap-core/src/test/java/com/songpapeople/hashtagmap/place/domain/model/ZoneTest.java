package com.songpapeople.hashtagmap.place.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ZoneTest {
    private Zone zone;

    @BeforeEach
    void setUp() {
        zone = Zone.builder()
                .topLeft(new Point("37.9", "127.1"))
                .bottomRight(new Point("37.1", "127.9"))
                .district(new District("서울시 송파구"))
                .build();
    }

    @DisplayName("position update 테스트")
    @Test
    void updatePosition() {
        zone.updatePosition("37.6", "127.3", "37.3", "127.5");

        assertAll(() -> {
            assertThat(zone.getMaxLatitude()).isEqualTo("37.3");
            assertThat(zone.getMinLatitude()).isEqualTo("37.6");
            assertThat(zone.getMaxLongitude()).isEqualTo("127.3");
            assertThat(zone.getMinLongitude()).isEqualTo("127.5");
        });
    }

    @DisplayName("District 변경 테스트")
    @Test
    void changeDistrict() {
        District replace = new District("서울시 강남구");
        zone.changeDistrict(replace);

        assertThat(zone.getDistrict()).isEqualToComparingFieldByField(replace);
    }
}