package com.songpapeople.hashtagmap.taglevel.service;

import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.AdminExceptionStatus;
import com.songpapeople.hashtagmap.taglevel.domain.TagLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TagLevelFactoryTest {

    @DisplayName("Hashtag 개수에 따라 TagLevel을 갱신한다.")
    @Test
    public void updateTest() {
        // given
        List<Long> hashtagCounts = LongStream.rangeClosed(100, 110)
                .boxed()
                .collect(Collectors.toList());
        List<TagLevel> tagLevels = Arrays.asList(new TagLevel(1L), new TagLevel(2L), new TagLevel(3L));

        // when
        List<TagLevel> actual = new TagLevelFactory().update(hashtagCounts, tagLevels);

        // then
        List<TagLevel> expected = Arrays.asList(
                new TagLevel(1L, 100L, 102L),
                new TagLevel(2L, 103L, 105L),
                new TagLevel(3L, 106L, 110L));
        assertThat(actual.get(0)).isEqualToComparingFieldByField(expected.get(0));
        assertThat(actual.get(1)).isEqualToComparingFieldByField(expected.get(1));
        assertThat(actual.get(2)).isEqualToComparingFieldByField(expected.get(2));
    }

    @DisplayName("Hashtag 개수가 TagLevel 개수보다 적을 때 예외가 발생한다.")
    @Test
    void updateExcepionTest() {
        List<Long> hashtagCounts = Arrays.asList(100L, 101L);
        List<TagLevel> tagLevels = Arrays.asList(new TagLevel(1L), new TagLevel(2L), new TagLevel(3L));

        AdminException exception = assertThrows(AdminException.class, ()
                -> new TagLevelFactory().update(hashtagCounts, tagLevels));
        assertThat(exception.getErrorCode()).isEqualTo(AdminExceptionStatus.INVALID_TAG_LEVEL_UPDATE.getCode());
    }
}