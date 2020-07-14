package com.songpapeople.hashtagmap.kakaoapi.domain;

public class PlaceRect {
    private static final String COMMA = ",";

    private Position left;
    private Position right;

    public PlaceRect(Position left, double xOffset, double yOffset) {
        this.left = left;
        this.right = left.add(xOffset, yOffset);
    }

    public String toKakaoFormat() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(left.getY())
                .append(COMMA)
                .append(left.getX())
                .append(COMMA)
                .append(right.getY())
                .append(COMMA)
                .append(right.getX());
        return stringBuilder.toString();
    }
}
