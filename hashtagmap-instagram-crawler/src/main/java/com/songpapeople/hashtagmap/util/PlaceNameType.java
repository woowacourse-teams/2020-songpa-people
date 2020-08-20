package com.songpapeople.hashtagmap.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public enum PlaceNameType {
    ORIGINAL_BRANCH("본점",
            placeName -> placeName.substring(0, placeName.length() - 2)),
    DIRECT_BRANCH("직영점",
            placeName -> placeName.substring(0, placeName.length() - 3)),
    CHINESE_BRANCH("반점",
            placeName -> placeName),
    BRANCH("점",
            placeName -> placeName.substring(0, placeName.length() - 1));

    private String suffix;
    private Function<String, String> expression;

    PlaceNameType(String suffix, Function<String, String> expression) {
        this.suffix = suffix;
        this.expression = expression;
    }

    public static Optional<PlaceNameType> find(String placeName) {
        return Arrays.stream(PlaceNameType.values())
                .filter(placeNameType -> placeName.endsWith(placeNameType.suffix))
                .findFirst();
    }

    public String parsePlaceName(String placeName) {
        return expression.apply(placeName);
    }
}
