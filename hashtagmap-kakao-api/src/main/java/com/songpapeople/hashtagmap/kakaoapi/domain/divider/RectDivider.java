package com.songpapeople.hashtagmap.kakaoapi.domain.divider;

import com.songpapeople.hashtagmap.kakaoapi.domain.Position;
import com.songpapeople.hashtagmap.kakaoapi.domain.Rect;

import java.util.ArrayList;
import java.util.List;

public class RectDivider {

    public static List<Rect> divide(Rect rect, double offset) {
        // todo 로직 개선
        List<Rect> rects = new ArrayList<>();
        for (double y = rect.getTopLeftY(); y > rect.getBottomRightY(); y -= offset) {
            for (double x = rect.getTopLeftX(); x < rect.getBottomRightX(); x += offset) {
                rects.add(new Rect(new Position(x, y), offset, offset));
            }
        }
        return rects;
    }
}
