package com.songpapeople.hashtagmap.kakaoapi.domain;

import lombok.Getter;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Getter
public class Rect {
    private static final String COMMA = ",";
    private static final int MAX_LATITUDE = 43;
    private static final int MIN_LATITUDE = 33;
    private static final int MAX_LONGITUDE = 132;
    private static final int MIN_LONGITUDE = 124;

    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

    public Rect(double x, double y, double offset) {
        this(x, x + offset, y, y + offset);
    }

    private Rect(double x1, double x2, double y1, double y2) {
        if (!isBetween(x1, MAX_LATITUDE, MIN_LATITUDE) || !isBetween(x2, MAX_LATITUDE, MIN_LATITUDE)
                || !isBetween(y1, MAX_LONGITUDE, MIN_LONGITUDE) || !isBetween(y2, MAX_LONGITUDE, MIN_LONGITUDE)) {
            throw new IllegalArgumentException("잘못된 좌표값을 입력하였습니다.");
        }
        this.minX = min(x1, x2);
        this.maxX = max(x1, x2);
        this.minY = min(y1, y2);
        this.maxY = max(y1, y2);
    }

    private boolean isBetween(double number, double max, double min) {
        return number >= min && number <= max;
    }

    public String toKakaoFormat() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(maxY)
                .append(COMMA)
                .append(minX)
                .append(COMMA)
                .append(minY)
                .append(COMMA)
                .append(maxX);
        return stringBuilder.toString();
    }
}
