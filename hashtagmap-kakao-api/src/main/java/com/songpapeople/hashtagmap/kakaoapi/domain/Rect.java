package com.songpapeople.hashtagmap.kakaoapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Rect {
    private static final String COMMA = ",";

    private Position topLeft;
    private Position bottomRight;

    public Rect(Position topLeft, double xOffset, double yOffset) {
        this.topLeft = topLeft;
        this.bottomRight = topLeft.add(xOffset, yOffset);
    }

    public String toKakaoFormat() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(topLeft.getY())
                .append(COMMA)
                .append(topLeft.getX())
                .append(COMMA)
                .append(bottomRight.getY())
                .append(COMMA)
                .append(bottomRight.getX());
        return stringBuilder.toString();
    }


    public double calculateXOffset() {
        return Math.abs(bottomRight.getX() - topLeft.getX());
    }

    public double calculateYOffset() {
        return Math.abs(topLeft.getY() - bottomRight.getY());
    }

    public double getTopLeftX() {
        return topLeft.getX();
    }

    public double getBottomRightX() {
        return bottomRight.getX();
    }

    public double getTopLeftY() {
        return topLeft.getY();
    }

    public double getBottomRightY() {
        return bottomRight.getY();
    }
}
