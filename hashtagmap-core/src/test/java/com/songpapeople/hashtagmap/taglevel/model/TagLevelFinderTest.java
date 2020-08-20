package com.songpapeople.hashtagmap.taglevel.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagLevelFinderTest {
    @DisplayName("각 Hashtag 개수에 해당하는 TagLevel을 결정한다.")
    @ParameterizedTest
    @CsvSource({"100,1", "110,1", "111,2", "130,3"})
    public void findTagLevelTest(Long hashtagCount, Long expected) {
        List<TagLevel> tagLevels = Arrays.asList(
                new TagLevel(1L, 100L, 110L),
                new TagLevel(2L, 111L, 120L),
                new TagLevel(3L, 121L, 130L)
        );

        Long actual = TagLevelFinder.findTagLevelIdByHashtagCount(tagLevels, hashtagCount);
        assertEquals(actual, expected);
    }

    @DisplayName("TagLevel에 포함되지 않을 경우 가장 작은 태그레벨로 처리한다.")
    @ParameterizedTest
    @CsvSource({"99,1", "131,1"})
    public void findTagLevelExceptionTest(Long hashtagCount, Long expected) {
        List<TagLevel> tagLevels = Arrays.asList(
                new TagLevel(1L, 100L, 110L),
                new TagLevel(2L, 111L, 120L),
                new TagLevel(3L, 121L, 130L)
        );

        Long actual = TagLevelFinder.findTagLevelIdByHashtagCount(tagLevels, hashtagCount);
        assertEquals(actual, expected);
    }
}