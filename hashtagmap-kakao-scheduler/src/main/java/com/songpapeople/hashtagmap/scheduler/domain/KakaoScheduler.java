package com.songpapeople.hashtagmap.scheduler.domain;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;

public class KakaoScheduler {
    private final ThreadPoolTaskScheduler scheduler;
    private final Runnable runnable;
    private final PeriodicTrigger periodicTrigger;

    public KakaoScheduler(Runnable runnable, PeriodicTrigger periodicTrigger) {
        this.scheduler = new ThreadPoolTaskScheduler();
        this.runnable = runnable;
        this.periodicTrigger = periodicTrigger;
    }

    public void start() {
        scheduler.initialize();
        scheduler.schedule(getRunnable(), getTrigger());
    }

    private Runnable getRunnable() {
        return runnable;
    }

    private Trigger getTrigger() {
        return periodicTrigger;
    }

    public void end() {
        scheduler.shutdown();
    }
}