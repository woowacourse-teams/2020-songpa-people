package com.songpapeople.hashtagmap.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlaceNameParserTest {
    @DisplayName("가게 이름 데이터 가공하기")
    @ParameterizedTest
    @CsvSource({"스타벅스 강남역점,스타벅스강남역", "피자나라 치킨공주,피자나라치킨공주"})
    void parsePlaceName(String placeName, String expected) {
        assertThat(PlaceNameParser.parsePlaceName(placeName)).isEqualTo(expected);
    }
}