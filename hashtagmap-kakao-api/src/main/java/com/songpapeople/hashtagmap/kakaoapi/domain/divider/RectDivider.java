package com.songpapeople.hashtagmap.kakaoapi.domain.divider;

import com.songpapeople.hashtagmap.kakaoapi.domain.Rect;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RectDivider {

    public static List<Rect> divide(Rect rect, double offset) {
        // todo 로직 개선
        BigDecimal convertedOffset = BigDecimal.valueOf(offset);
        BigDecimal minX = BigDecimal.valueOf(rect.getMinX());
        BigDecimal maxX = BigDecimal.valueOf(rect.getMaxX());
        BigDecimal minY = BigDecimal.valueOf(rect.getMinY());
        BigDecimal maxY = BigDecimal.valueOf(rect.getMaxY());
        List<Rect> rects = new ArrayList<>();

        for (BigDecimal y = maxY.subtract(convertedOffset); isGreater(y, minY); y = y.subtract(convertedOffset)) {
            for (BigDecimal x = minX; isLess(x, maxX); x = x.add(convertedOffset)) {
                rects.add(new Rect(x.doubleValue(), y.doubleValue(), offset));
            }
        }
        return rects;
    }

    private static boolean isGreater(BigDecimal standard, BigDecimal compareNumber) {
        return standard.compareTo(compareNumber) > -1;
    }

    private static boolean isLess(BigDecimal standard, BigDecimal compareNumber) {
        return standard.compareTo(compareNumber) < 0;
    }
}
