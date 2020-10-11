package com.songpapeople.hashtagmap.event.process;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.Semaphore;

@Slf4j
public class EventThreadPoolExecutor {
    private final Semaphore semaphore;
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public EventThreadPoolExecutor(EventType eventType) {
        this.threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        this.semaphore = new Semaphore(eventType.getMaxPoolSize());
        threadPoolTaskExecutor.setThreadNamePrefix("thread_" + eventType.getTypeName());
        threadPoolTaskExecutor.setCorePoolSize(eventType.getCorePoolSize()); //수행 풀
        threadPoolTaskExecutor.setMaxPoolSize(eventType.getMaxPoolSize()); // 코어가 꽉 차면 맥스까지 늘어남
        threadPoolTaskExecutor.setQueueCapacity(eventType.getQueueCapacity()); // 대기할 수 있는 작업 수
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.initialize();

        // log 컨텍스트를 callback 을 이용해서 복사 등록한다.
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        threadPoolTaskExecutor.setTaskDecorator(runnable -> () -> {
            try {
                MDC.setContextMap(contextMap);
                runnable.run();
            } finally {
                MDC.clear();
            }
        });
    }

    public void executeJob(Runnable receiveJob, Runnable failJob) {
        try {
            semaphore.acquire();
            threadPoolTaskExecutor.execute(() -> execute(receiveJob, failJob));
        } catch (InterruptedException e) {
            log.warn("semaphore interrupted: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void execute(Runnable job, Runnable failJob) {
        try {
            job.run();
        } catch (Exception e) {
            log.warn("event fail: {}", e.getMessage());
            failJob.run();
        } finally {
            semaphore.release();
        }
    }
}
