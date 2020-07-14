package com.songpapeople.hashtagmap.kakaoapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Position {
    private double x;
    private double y;

    public Position add(double xOffset, double yOffset) {
        return new Position(this.x + xOffset, this.y + yOffset);
    }
}

