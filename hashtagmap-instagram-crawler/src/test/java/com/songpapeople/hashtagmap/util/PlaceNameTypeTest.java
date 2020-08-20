package com.songpapeople.hashtagmap.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaceNameTypeTest {
    private static Stream<Arguments> providePlaceNameTypeByPlaceName() {
        return Stream.of(
                Arguments.of("스타벅스", Optional.empty()),
                Arguments.of("스타벅스 본점", Optional.of(PlaceNameType.ORIGINAL_BRANCH)),
                Arguments.of("스타벅스 직영점", Optional.of(PlaceNameType.DIRECT_BRANCH)),
                Arguments.of("스타벅스 잠실점", Optional.of(PlaceNameType.BRANCH)),
                Arguments.of("홍콩반점", Optional.of(PlaceNameType.CHINESE_BRANCH))
        );
    }

    @DisplayName("가게 이름에 맞는 PlaceNameType이 반환되는지 테스트")
    @ParameterizedTest
    @MethodSource("providePlaceNameTypeByPlaceName")
    void findTest(String placeName, Optional<PlaceNameType> placeNameType) {
        Optional<PlaceNameType> optionalPlaceNameType = PlaceNameType.find(placeName);
        assertThat(optionalPlaceNameType).isEqualTo(placeNameType);
    }
}
