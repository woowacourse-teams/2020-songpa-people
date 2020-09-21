package com.songpapeople.hashtagmap.kakao.schedule.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PeriodHistoryTest {

    @DisplayName("PeriodHistory 생성자 테스트")
    @Test
    void create() {
        PeriodHistory periodHistory = new PeriodHistory("0 0 * * * ?");
        assertAll(() -> {
            assertThat(periodHistory.getMember()).isNull();
            assertThat(periodHistory.getExpression()).isEqualTo("0 0 * * * ?");
        });
    }
}