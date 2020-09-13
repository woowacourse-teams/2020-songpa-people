package com.songpapeople.hashtagmap.event.process;

import com.songpapeople.hashtagmap.event.message.Event;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class EventConsumer {
    private final EventBrokerGroup eventBrokerGroup;
    private final Map<Class<? extends Event>, EventThreadPoolExecutor> threadPoolExecutors = new HashMap<>();

    private boolean runnable = true;

    public EventConsumer(final EventBrokerGroup eventBrokerGroup) {
        this.eventBrokerGroup = eventBrokerGroup;
        for (Class<? extends Event> eventClass : eventBrokerGroup.keySet()) {
            Thread thread = new Thread(() -> consume(eventClass));
            thread.setName("thread_" + eventClass.getSimpleName());
            threadPoolExecutors.put(eventClass, new EventThreadPoolExecutor(EventType.findBy(eventClass)));
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
                    .executeJob(() -> maybeEvent.get().doService());
        }
    }

}
