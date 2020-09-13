package com.songpapeople.hashtagmap.event.process;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

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
        threadPoolTaskExecutor.setMaxPoolSize(eventType.getMaxPoolSize()); // 큐에 추가되면 맥스까지 늘어남
//        threadPoolTaskExecutor.setQueueCapacity(eventType.getQueueCapacity()); // 대기할 수 있는 작업 수
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.initialize();
    }

    public void executeJob(Runnable receiveJob) {
        try {
            semaphore.acquire();
            threadPoolTaskExecutor.execute(() -> execute(receiveJob));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void execute(Runnable job) {
        try {
            job.run();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            semaphore.release();
        }
    }
}
