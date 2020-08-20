package com.songpapeople.hashtagmap.util;

import java.util.Optional;

public class PlaceNameParser {
    public static String parsePlaceName(String placeName) {
        String parsedPlaceName = placeName.replaceAll(" ", "");
        Optional<PlaceNameType> optionalPlaceNameType = PlaceNameType.find(parsedPlaceName);
        if (optionalPlaceNameType.isPresent()) {
            PlaceNameType placeNameType = optionalPlaceNameType.get();
            return placeNameType.parsePlaceName(parsedPlaceName);
        }
        return parsedPlaceName;
    }
}
