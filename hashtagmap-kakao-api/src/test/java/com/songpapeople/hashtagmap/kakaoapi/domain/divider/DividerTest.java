package com.songpapeople.hashtagmap.kakaoapi.domain.divider;

import com.songpapeople.hashtagmap.kakaoapi.domain.Position;
import com.songpapeople.hashtagmap.kakaoapi.domain.Rect;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DividerTest {

    @DisplayName("입력받은 사각형을 offset 으로 나누기")
    @Test
    void divideRectTest() {
        Position topLeft = new Position(1, 10);
        Position bottomRight = new Position(1.2, 9.6);
        Rect rect = new Rect(topLeft, bottomRight);

        List<Rect> rects = RectDivider.divide(rect, 0.02);

        assertThat(rects.size()).isEqualTo(210);
    }
}
