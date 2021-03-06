package com.songpapeople.hashtagmap.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Constructor;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlaceNameParserTest {
    @DisplayName("가게 이름 데이터 가공하기")
    @ParameterizedTest
    @CsvSource({"스타벅스 강남역점,스타벅스강남역", "피자나라 치킨공주,피자나라치킨공주", "스타벅스,스타벅스",
            "홍콩반점,홍콩반점", "스타벅스 본점,스타벅스", "스타벅스 직영점,스타벅스"})
    void parsePlaceName(String placeName, String expected) {
        assertThat(PlaceNameParser.parsePlaceName(placeName)).isEqualTo(expected);
    }

    @DisplayName("유틸성 클래스라 private 생성자를 가지고 있다.")
    @Test
    public void constructorTest() throws NoSuchMethodException {
        Constructor<PlaceNameParser> declaredConstructor = PlaceNameParser.class.getDeclaredConstructor((Class<?>[]) null);
        Assertions.assertThat(declaredConstructor.isAccessible()).isFalse();
    }
}