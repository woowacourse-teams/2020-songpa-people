package com.songpapeople.hashtagmap.taglevel.service;

import com.songpapeople.hashtagmap.instagram.domain.model.HashtagCounts;
import com.songpapeople.hashtagmap.taglevel.model.HashtagCountsDivider;
import com.songpapeople.hashtagmap.taglevel.model.TagLevel;
import com.songpapeople.hashtagmap.taglevel.model.TagLevels;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HashtagCountsDividerTest {

    @DisplayName("HashtagCountsDividerTest")
    @TestFactory
    Collection<DynamicTest> hashtagCountsDividerTest() {
        HashtagCounts hashtagCounts = new HashtagCounts(LongStream.range(100, 110)
                .boxed()
                .collect(Collectors.toList()));
        TagLevels tagLevels = new TagLevels(
                Arrays.asList(new TagLevel(1L), new TagLevel(2L), new TagLevel(3L))
        );

        HashtagCountsDivider divider = new HashtagCountsDivider(tagLevels, hashtagCounts);

        return Arrays.asList(
                DynamicTest.dynamicTest("적절한 최소 범위를 계산한다.", () -> {
                    assertAll(
                            () -> assertThat(divider.getMinHashtagCountByTagLevel(0)).isEqualTo(100L),
                            () -> assertThat(divider.getMinHashtagCountByTagLevel(1)).isEqualTo(103L),
                            () -> assertThat(divider.getMinHashtagCountByTagLevel(2)).isEqualTo(106L)
                    );
                }),
                DynamicTest.dynamicTest("적절한 최대 범위를 계산한다.", () -> {
                    assertAll(
                            () -> assertThat(divider.getMaxHashtagCountByTagLevel(0)).isEqualTo(102L),
                            () -> assertThat(divider.getMaxHashtagCountByTagLevel(1)).isEqualTo(105L),
                            () -> assertThat(divider.getMaxHashtagCountByTagLevel(2)).isEqualTo(109L)
                    );
                })
        );
    }
}