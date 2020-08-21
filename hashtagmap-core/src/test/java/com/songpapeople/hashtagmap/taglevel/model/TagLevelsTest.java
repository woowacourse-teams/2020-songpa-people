package com.songpapeople.hashtagmap.taglevel.model;

import com.songpapeople.hashtagmap.exception.CoreException;
import com.songpapeople.hashtagmap.exception.CoreExceptionStatus;
import com.songpapeople.hashtagmap.instagram.domain.model.HashtagCounts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TagLevelsTest {

    @DisplayName("TagLevel 최소,최대 해시태그 개수를 갱신한다.")
    @Test
    public void updateTest() {
        // given
        TagLevels tagLevels = new TagLevels(Arrays.asList(new TagLevel(), new TagLevel()));
        HashtagCounts hashtagCounts = new HashtagCounts(Arrays.asList(1L, 2L, 3L, 4L));

        // when
        TagLevels actual = tagLevels.update(hashtagCounts);

        // then
        assertThat(actual.get(0).getMinHashtagCount()).isEqualTo(1L);
        assertThat(actual.get(0).getMaxHashtagCount()).isEqualTo(2L);
        assertThat(actual.get(1).getMinHashtagCount()).isEqualTo(3L);
        assertThat(actual.get(1).getMaxHashtagCount()).isEqualTo(4L);
    }

    @DisplayName("각 Hashtag 개수에 해당하는 TagLevel을 결정한다.")
    @ParameterizedTest
    @CsvSource({"100,1", "110,1", "111,2", "130,3"})
    public void findTagLevelTest(Long hashtagCount, Long expected) {
        TagLevels tagLevels = new TagLevels(
                Arrays.asList(
                        new TagLevel(1L, 100L, 110L),
                        new TagLevel(2L, 111L, 120L),
                        new TagLevel(3L, 121L, 130L)
                )
        );

        Long actual = tagLevels.findIdByHashtagCount(hashtagCount);
        assertEquals(actual, expected);
    }

    @DisplayName("TagLevel에 포함되지 않을 경우 가장 작은 태그레벨로 처리한다.")
    @ParameterizedTest
    @CsvSource({"99,1", "131,1"})
    public void findTagLevelExceptionTest(Long hashtagCount, Long expected) {
        TagLevels tagLevels = new TagLevels(
                Arrays.asList(
                        new TagLevel(1L, 100L, 110L),
                        new TagLevel(2L, 111L, 120L),
                        new TagLevel(3L, 121L, 130L)
                )
        );

        Long actual = tagLevels.findIdByHashtagCount(hashtagCount);
        assertEquals(actual, expected);
    }

    @DisplayName("태그레벨 갱신 시, Hashtag 개수를 검증한다.")
    @Test
    void validateSizeTest() {
        HashtagCounts hashtagCounts = new HashtagCounts(Arrays.asList(1L));
        TagLevels tagLevels = new TagLevels(
                Arrays.asList(new TagLevel(1L), new TagLevel(2L), new TagLevel(3L))
        );

        CoreException exception = assertThrows(CoreException.class,
                () -> tagLevels.update(hashtagCounts));
        assertEquals(exception.getErrorCode(), CoreExceptionStatus.INVALID_TAG_LEVEL.getCode());
    }
}