package com.songpapeople.hashtagmap.taglevel.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TagLevelsTest {

    @DisplayName("TagLevel 최소,최대 해시태그 개수를 갱신한다.")
    @Test
    public void updateTest() {
        // given
        TagLevels tagLevels = new TagLevels(Arrays.asList(new TagLevel()));

        // when
        tagLevels.update(0, 0L, 10L);

        // then
        assertThat(tagLevels.get(0).getMinHashtagCount()).isEqualTo(0L);
        assertThat(tagLevels.get(0).getMaxHashtagCount()).isEqualTo(10L);
    }

    @DisplayName("마지막 index 확인")
    @Test
    public void getLastIndexTest() {
        // given
        TagLevels tagLevels = new TagLevels(Arrays.asList(new TagLevel()));

        assertThat(tagLevels.isLastIndex(0)).isTrue();
    }
}