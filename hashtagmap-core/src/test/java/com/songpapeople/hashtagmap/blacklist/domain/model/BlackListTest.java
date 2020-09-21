package com.songpapeople.hashtagmap.blacklist.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BlackListTest {

    @DisplayName("blackList 업데이트 기능 테스트")
    @Test
    void updateBlackList() {
        BlackList target = new BlackList("1", "오아시스");
        BlackList replace = new BlackList("1", "오아시스강남", true);

        target.updateBlackList(replace);

        assertThat(target.getReplaceName()).isEqualTo("오아시스강남");
        assertThat(target.getIsSkipPlace()).isTrue();
    }
}
