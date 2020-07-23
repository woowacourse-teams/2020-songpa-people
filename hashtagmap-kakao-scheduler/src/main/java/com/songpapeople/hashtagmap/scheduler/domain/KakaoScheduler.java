package com.songpapeople.hashtagmap.scheduler.domain;

import com.songpapeople.hashtagmap.scheduler.service.KakaoSchedulerService;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;

public class KakaoScheduler {
    private final KakaoSchedulerService kakaoSchedulerService;
    private final ThreadPoolTaskScheduler scheduler;
    private final PeriodicTrigger periodicTrigger;

    public KakaoScheduler(KakaoSchedulerService kakaoSchedulerService, PeriodicTrigger periodicTrigger) {
        this.kakaoSchedulerService = kakaoSchedulerService;
        this.scheduler = new ThreadPoolTaskScheduler();
        this.periodicTrigger = periodicTrigger;
    }

    public void start() {
        scheduler.initialize();
        scheduler.schedule(getRunnable(), getTrigger());
    }

    private Runnable getRunnable() {
        return kakaoSchedulerService::collectData;
    }

    private Trigger getTrigger() {
        return periodicTrigger;
    }

    public void end() {
        scheduler.shutdown();
    }
}
