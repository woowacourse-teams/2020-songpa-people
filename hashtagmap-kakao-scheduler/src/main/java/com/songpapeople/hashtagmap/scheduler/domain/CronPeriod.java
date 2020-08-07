package com.songpapeople.hashtagmap.scheduler.domain;

import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerException;
import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerExceptionStatus;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;

import java.util.TimeZone;

public class CronPeriod {
    private static final TimeZone TIME_SEOUL = TimeZone.getTimeZone("Asia/Seoul");
    private Trigger trigger;

    public CronPeriod(String expression) {
        validateCronTrigger(expression);
        this.trigger = new CronTrigger(expression, TIME_SEOUL);
    }

    public void change(final String expression) {
        validateCronTrigger(expression);
        this.trigger = new CronTrigger(expression, TIME_SEOUL);
    }

    private void validateCronTrigger(String expression) {
        try {
            new CronTrigger(expression, TIME_SEOUL);
        } catch (IllegalArgumentException e) {
            throw new KakaoSchedulerException(KakaoSchedulerExceptionStatus.INVALID_PERIOD_EXPRESSION);
        }
    }

    public Trigger getTrigger() {
        return trigger;
    }
}
