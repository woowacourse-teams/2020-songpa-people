package com.songpapeople.hashtagmap.scheduler.domain;

import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerException;
import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerExceptionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CronPeriodTest {

    @DisplayName("(예외) 잘못된 정규화식을 입력했을 때")
    @ParameterizedTest
    @ValueSource(strings = {"", "* * * * * * /", "* - * - *", "L * * * * *"})
    public void CronPeriodTest(String wronExpression) {
        KakaoSchedulerException excpetion = assertThrows(KakaoSchedulerException.class,
                () -> new CronPeriod(wronExpression));

        KakaoSchedulerExceptionStatus exceptionStatus = KakaoSchedulerExceptionStatus.INVALID_PERIOD_EXPRESSION;
        assertThat(excpetion.getErrorCode()).isEqualTo(exceptionStatus.getStatusCode());
        assertThat(excpetion.getMessage()).isEqualTo(exceptionStatus.getMessage());
    }
}