package com.songpapeople.hashtagmap.scheduler.domain;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;

import java.util.TimeZone;

public class CronPeriod {
    private static final TimeZone TIME_SEOUL = TimeZone.getTimeZone("Asia/Seoul");
    private Trigger trigger;

    public CronPeriod(String expression) {
        this.trigger = new CronTrigger(expression, TIME_SEOUL);
    }

    public void change(final String expression) {
        this.trigger = new CronTrigger(expression, TIME_SEOUL);
    }

    public Trigger getTrigger() {
        return trigger;
    }

}
