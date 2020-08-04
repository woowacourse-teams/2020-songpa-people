package com.songpapeople.hashtagmap.util;

public class PlaceNameParser {
    private static final int FIRST_INDEX = 0;

    public static String parsePlaceName(String placeName) {
        String parsedPlaceName = placeName.replaceAll(" ", "");
        if (parsedPlaceName.endsWith("점")) {
            return parsedPlaceName.substring(FIRST_INDEX, parsedPlaceName.length() - 1);
        }
        return parsedPlaceName;
    }
}
