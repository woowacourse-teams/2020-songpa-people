package com.songpapeople.hashtagmap.kakaoapi.domain.rect.location;

import java.math.BigDecimal;

public abstract class Location {
    protected final BigDecimal value;

    public Location(BigDecimal value) {
        this.value = value;
    }

    protected static boolean isBetween(BigDecimal value, BigDecimal min, BigDecimal max) {
        return value.compareTo(max) <= 0
                && value.compareTo(min) >= 0;
    }

    public double getValue() {
        return value.doubleValue();
    }

    public abstract Location forward(BigDecimal offset);
}
