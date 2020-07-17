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

        for (Location y = maxLongitude; isGreater(y, minLongitude); y = y.forward(offset)) {
            for (Location x = minLatitude; isLess(x, maxLatitude); x = x.forward(offset)) {
                rects.add(new Rect(x, y, offset));
            }
        }
        return rects;
    }

    private static boolean isGreater(Location standard, Location compare) {
        return standard.getValue() > compare.getValue();
    }

    private static boolean isLess(Location standard, Location compare) {
        return standard.getValue() < compare.getValue();
    }
}
