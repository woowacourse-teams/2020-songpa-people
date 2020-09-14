package com.songpapeople.hashtagmap.scheduler.domain.factory;

import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Latitude;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Longitude;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RectFactoryTest {
    @DisplayName("Zone을 Rect로 변환한다")
    @Test
    public void zoneToRectTest() {
        Zone zone = Zone.builder()
                .topLeft(new Point("33", "130"))
                .bottomRight(new Point("35", "128"))
                .district(new District("서울시 송파구"))
                .isActivated(true)
                .build();
        Rect rect = Rect.byOffset(new Latitude(33), new Longitude(130), 2);

        assertThat(RectFactory.from(zone)).isEqualTo(rect);
    }

    @DisplayName("List<Zone>을 List<Rect>로 변환한다")
    @Test
    public void listOfZoneToRectTest() {
        Zone zone = Zone.builder()
                .topLeft(new Point("33", "130"))
                .bottomRight(new Point("35", "128"))
                .district(new District("서울시 송파구"))
                .isActivated(true)
                .build();
        List<Zone> zones = Arrays.asList(zone);

        Rect rect = Rect.byOffset(new Latitude(33), new Longitude(130), 2);

        List<Rect> rects = RectFactory.from(zones);

        assertThat(rects.get(0)).isEqualTo(rect);
    }
}
