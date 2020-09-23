package com.songpapeople.hashtagmap.event.process;

import com.songpapeople.hashtagmap.event.message.Event;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EventConsumer {
    private final EventBrokerGroup eventBrokerGroup;
    private final Map<Class<? extends Event>, EventThreadPoolExecutor> threadPoolExecutors = new HashMap<>();

    private boolean runnable = true;

    public EventConsumer(final EventBrokerGroup eventBrokerGroup) {
        this.eventBrokerGroup = eventBrokerGroup;
        startConsume();
    }

    private void startConsume() {
        for (Class<? extends Event> eventClass : this.eventBrokerGroup.keySet()) {
            threadPoolExecutors.put(eventClass, new EventThreadPoolExecutor(EventType.findBy(eventClass)));
            Thread thread = new Thread(() -> consume(eventClass));
            thread.setName("thread_" + eventClass.getSimpleName());
            thread.start();
        }
    }

    private void consume(Class<? extends Event> eventType) {
        while (runnable) {
            Optional<? extends Event> maybeEvent = eventBrokerGroup.poll(eventType);
            if (!maybeEvent.isPresent()) {
                continue;
            }
            threadPoolExecutors.get(eventType)
                    .executeJob(() -> maybeEvent.get().doService(),
                            () -> maybeEvent.get().doFailService());
        }
    }

    public void stop() {
        this.runnable = false;
    }

}
