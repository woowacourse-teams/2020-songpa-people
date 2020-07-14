package com.songpapeople.hashtagmap.kakaoapi.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RectTest {

    @DisplayName("topLeft와 rightBottom의 x 값 차이를 구한다")
    @Test
    void calculateXOffsetTest() {
        Rect rect = new Rect(new Position(2, 1.5), new Position(1.5, 7.5));
        double xOffset = rect.calculateXOffset();

        assertThat(xOffset).isEqualTo(0.5);
    }

    @DisplayName("topLeft와 rightBottom의 y 값 차이를 구한다")
    @Test
    void calculateYOffsetTest() {
        Rect rect = new Rect(new Position(2, 1.5), new Position(1.5, 7.5));
        double yOffset = rect.calculateYOffset();

        assertThat(yOffset).isEqualTo(6);
    }
}
