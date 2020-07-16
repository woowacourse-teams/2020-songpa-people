package com.songpapeople.hashtagmap.kakaoapi.domain.divider;

import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.RectDivider;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Latitude;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Longitude;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DividerTest {

    @DisplayName("입력받은 사각형을 offset 으로 나누기")
    @Test
    void divideRectTest() {
        Rect rect = new Rect(new Latitude(33), new Longitude(126.5), 0.1);

        List<Rect> rects = RectDivider.divide(rect, BigDecimal.valueOf(0.01));

        assertThat(rects.size()).isEqualTo(100);
    }
}
