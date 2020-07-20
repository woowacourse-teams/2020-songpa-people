package com.songpapeople.hashtagmap.kakaoapi.domain.rect;

import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Latitude;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Longitude;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RectDividerTest {
    @DisplayName("InitialRect를 offset 으로 나누기")
    @Test
    void divideRectTest() {
        Rect rect = new Rect(new Latitude(33), new Longitude(126.5), 0.1);

        List<Rect> rects = RectDivider.divide(rect, BigDecimal.valueOf(0.01));

        assertThat(rects.size()).isEqualTo(100);
    }

    @DisplayName("InitialRect를 나눈 DividedRect를 검증")
    @Test
    void name() {
        Rect rect = new Rect(new Latitude(33), new Longitude(126), 0.04);
        List<Rect> dividedRects = RectDivider.divide(rect, BigDecimal.valueOf(0.02));

        assertThat(dividedRects).hasSize(4);
        assertThat(dividedRects).contains(new Rect(new Latitude(33), new Longitude(126), 0.02));
        assertThat(dividedRects).contains(new Rect(new Latitude(33 + 0.02), new Longitude(126), 0.02));
        assertThat(dividedRects).contains(new Rect(new Latitude(33), new Longitude(126 - 0.02), 0.02));
        assertThat(dividedRects).contains(new Rect(new Latitude(33 + 0.02), new Longitude(126 - 0.02), 0.02));
    }
}
