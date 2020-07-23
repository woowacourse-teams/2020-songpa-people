package com.songpapeople.hashtagmap.scheduler.domain;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;

public class KakaoPlaceScheduler {
    private final ThreadPoolTaskScheduler scheduler;
    private final PeriodicTrigger periodicTrigger;

    public KakaoPlaceScheduler(PeriodicTrigger periodicTrigger) {
        this.scheduler = new ThreadPoolTaskScheduler();
        this.periodicTrigger = periodicTrigger;
    }

    public void start() {
        scheduler.initialize();
        scheduler.schedule(getRunnable(), getTrigger());
    }

    private Runnable getRunnable() {
        return () -> {

        };
    }

    private Trigger getTrigger() {
        return periodicTrigger;
    }

    public void end() {
        scheduler.shutdown();
    }
}
