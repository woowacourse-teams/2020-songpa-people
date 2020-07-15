package com.songpapeople.hashtagmap.kakaoapi.domain.divider;

import com.songpapeople.hashtagmap.kakaoapi.domain.Rect;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DividerTest {

    @DisplayName("입력받은 사각형을 offset 으로 나누기")
    @Test
    void divideRectTest() {
        Rect rect = new Rect(33.5, 126.5, 0.1);

        List<Rect> rects = RectDivider.divide(rect, 0.01);

        assertThat(rects.size()).isEqualTo(100);
    }
}
