package com.songpapeople.hashtagmap.kakaoapi.domain.rect.location;

import java.math.BigDecimal;

public abstract class Coordinate {
    protected final BigDecimal value;

    public Coordinate(BigDecimal value) {
        this.value = value;
    }

    protected static boolean isBetween(BigDecimal value, BigDecimal min, BigDecimal max) {
        return value.compareTo(max) <= 0
                && value.compareTo(min) >= 0;
    }

    public boolean isGreater(Coordinate compare) {
        return this.getValue() > compare.getValue();
    }

    public boolean isLess(Coordinate compare) {
        return this.getValue() < compare.getValue();
    }

    public double getValue() {
        return value.doubleValue();
    }

    public abstract Coordinate forward(BigDecimal offset);
}
