package com.songpapeople.hashtagmap.kakaoapi.domain.rect;

import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Coordinate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RectDivider {
    public static List<Rect> divide(Rect rect, BigDecimal offset) {
        Coordinate minLatitude = rect.getMinLatitude();
        Coordinate maxLatitude = rect.getMaxLatitude();
        Coordinate minLongitude = rect.getMinLongitude();
        Coordinate maxLongitude = rect.getMaxLongitude();
        List<Rect> rects = new ArrayList<>();

        for (Coordinate y = maxLongitude; y.isGreater(minLongitude); y = y.forward(offset)) {
            for (Coordinate x = minLatitude; x.isLess(maxLatitude); x = x.forward(offset)) {
                rects.add(Rect.byOffset(x, y, offset));
            }
        }
        return rects;
    }
}
