package com.songpapeople.hashtagmap.kakaoapi.domain.rect;

import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Location;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RectDivider {
    public static List<Rect> divide(Rect rect, BigDecimal offset) {
        Location minLatitude = rect.getMinLatitude();
        Location maxLatitude = rect.getMaxLatitude();
        Location minLongitude = rect.getMinLongitude();
        Location maxLongitude = rect.getMaxLongitude();
        List<Rect> rects = new ArrayList<>();

        for (Location y = maxLongitude; y.isGreater(minLongitude); y = y.forward(offset)) {
            for (Location x = minLatitude; x.isLess(maxLatitude); x = x.forward(offset)) {
                rects.add(Rect.byOffset(x, y, offset));
            }
        }
        return rects;
    }
}
