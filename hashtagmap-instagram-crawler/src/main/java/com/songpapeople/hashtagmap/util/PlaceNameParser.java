package com.songpapeople.hashtagmap.util;

import java.util.Optional;

public class PlaceNameParser {

    private PlaceNameParser() {
    }

    public static String parsePlaceName(String placeName) {
        String parsedPlaceName = placeName.replaceAll(" ", "");
        Optional<PlaceNameType> optionalPlaceNameType = PlaceNameType.find(parsedPlaceName);
        return optionalPlaceNameType
                .map(placeNameType -> placeNameType.parsePlaceName(parsedPlaceName))
                .orElseGet(() -> parsedPlaceName);
    }
}
