package com.songpapeople.hashtagmap.taglevel.model;

import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.instagram.domain.model.HashtagCounts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class HashtagCountsDividerTest {

    @DisplayName("HashtagCountsDividerTest")
    @TestFactory
    Collection<DynamicTest> hashtagCountsDividerTest() {
        HashtagCounts hashtagCounts = new HashtagCounts(LongStream.range(100, 110)
                .boxed()
                .collect(Collectors.toList()));
        TagLevels tagLevels = new TagLevels(
                Arrays.asList(new TagLevel(1L), new TagLevel(2L))
        );

        HashtagCountsDivider divider = new HashtagCountsDivider(tagLevels, hashtagCounts);

        return Arrays.asList(
                DynamicTest.dynamicTest("적절한 최소 범위를 계산한다.", () -> {
                    assertAll(
                            () -> assertThat(divider.getMinHashtagCountByTagLevel(0)).isEqualTo(100L),
                            () -> assertThat(divider.getMinHashtagCountByTagLevel(1)).isEqualTo(105L)
                    );
                }),
                DynamicTest.dynamicTest("적절한 최대 범위를 계산한다.", () -> {
                    assertAll(
                            () -> assertThat(divider.getMaxHashtagCountByTagLevel(0)).isEqualTo(104L),
                            () -> assertThat(divider.getMaxHashtagCountByTagLevel(1)).isEqualTo(109L)
                    );
                })
        );
    }

    @DisplayName("Hashtag 개수가 태그레벨 개수보다 적어 갱신할 수 없을 때 예외처리")
    @Test
    void invalidSizeException() {
        HashtagCounts hashtagCounts = new HashtagCounts(Arrays.asList(1L));
        TagLevels tagLevels = new TagLevels(Arrays.asList(new TagLevel(1L), new TagLevel(2L)));

        assertThatThrownBy(() -> new HashtagCountsDivider(tagLevels, hashtagCounts))
                .isInstanceOf(AdminException.class);
    }
}