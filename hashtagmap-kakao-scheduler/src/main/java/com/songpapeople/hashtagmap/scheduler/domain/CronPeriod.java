package com.songpapeople.hashtagmap.scheduler.domain;

import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerExceptionStatus;
import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerExcpetion;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;

import java.util.TimeZone;

public class CronPeriod {
    private static final TimeZone TIME_SEOUL = TimeZone.getTimeZone("Asia/Seoul");
    private Trigger trigger;

    public CronPeriod(String expression) {
        this.trigger = validateCronTrigger(expression);
    }

    public void change(final String expression) {
        this.trigger = validateCronTrigger(expression);
    }

    private CronTrigger validateCronTrigger(String expression) {
        try {
            return new CronTrigger(expression, TIME_SEOUL);
        } catch (IllegalArgumentException e) {
            throw new KakaoSchedulerExcpetion(KakaoSchedulerExceptionStatus.INVALID_PERIOD_EXPRESSION);
        }
    }

    public Trigger getTrigger() {
        return trigger;
    }
}
