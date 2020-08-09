package com.songpapeople.hashtagmap.scheduler.domain;

import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerErrorCode;
import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CronPeriodTest {

    @DisplayName("(예외) 잘못된 정규화식을 입력했을 때")
    @ParameterizedTest
    @ValueSource(strings = {"", "* * * * * * /"})
    public void CronPeriodTest(String wronExpression) {
        KakaoSchedulerException excpetion = assertThrows(KakaoSchedulerException.class,
                () -> new CronPeriod(wronExpression));

        KakaoSchedulerErrorCode kakaoSchedulerErrorCode = KakaoSchedulerErrorCode.INVALID_PERIOD_EXPRESSION;
        assertThat(excpetion.getErrorCode()).isEqualTo(kakaoSchedulerErrorCode.getCode());
        assertThat(excpetion.getMessage()).isEqualTo(kakaoSchedulerErrorCode.getMessage());
    }
}