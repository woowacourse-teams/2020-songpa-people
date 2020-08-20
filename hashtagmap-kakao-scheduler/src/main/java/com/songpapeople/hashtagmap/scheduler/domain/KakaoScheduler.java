package com.songpapeople.hashtagmap.scheduler.domain;

import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerException;
import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerExceptionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledFuture;

@Slf4j
public class KakaoScheduler {
    private final ThreadPoolTaskScheduler scheduler;
    private final CronPeriod cronPeriod;
    private final Runnable runnable;
    private ScheduledFuture<?> scheduledFuture;

    public KakaoScheduler(Runnable runnable, CronPeriod cronPeriod) {
        this.scheduler = new ThreadPoolTaskScheduler();
        this.runnable = runnable;
        this.cronPeriod = cronPeriod;
        this.scheduler.setWaitForTasksToCompleteOnShutdown(true);
        this.scheduler.initialize();
    }

    public void start() {
        if (isActive()) {
            log.info("KakaoScheduler already running");
            throw new KakaoSchedulerException(KakaoSchedulerExceptionStatus.SCHEDULE_ALREADY_RUNNING);
        }
        this.scheduledFuture = this.scheduler.schedule(this.runnable, getTrigger());
        log.info("KakaoScheduler started at : " + LocalDateTime.now());
    }

    public boolean isActive() {
        return this.scheduledFuture != null && !this.scheduledFuture.isCancelled();
    }

    public boolean isNotActive() {
        return !isActive();
    }

    private Trigger getTrigger() {
        return this.cronPeriod.getTrigger();
    }

    @PreDestroy
    public void end() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.scheduler.shutdown();
        log.info("KakaoScheduler destroyed at : " + LocalDateTime.now());
    }

    public void changePeriod(String expression) {
        if (isActive()) {
            log.info("Can't change period, KakaoScheduler is running");
            throw new KakaoSchedulerException(KakaoSchedulerExceptionStatus.SCHEDULE_ALREADY_RUNNING);
        }
        this.cronPeriod.change(expression);
        log.info("KakaoScheduler cron period changed at : " + LocalDateTime.now());
    }

    public boolean stop() {
        if (isNotActive()) {
            log.info("KakaoScheduler already stopped");
            throw new KakaoSchedulerException(KakaoSchedulerExceptionStatus.SCHEDULE_ALREADY_STOPPED);
        }
        this.scheduledFuture.cancel(true);
        log.info("KakaoScheduler stopped at : " + LocalDateTime.now());
        return this.scheduledFuture.isCancelled();
    }

    public void syncSchedule(Schedule schedule) {
        if (schedule.isActive() && this.isNotActive()) {
            log.info("Force Sync Schedule to Stop");
            schedule.toggle();
        }
        if (schedule.isNotActive() && this.isActive()) {
            log.info("Force Sync Schedule to Start");
            schedule.toggle();
        }
    }

}