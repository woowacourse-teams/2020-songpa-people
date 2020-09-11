package com.songpapeople.hashtagmap.event.process;

import com.songpapeople.hashtagmap.event.model.EventHistory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class EventBrokerGroup {
    private final Map<Class<? extends EventHistory>, EventBroker<? extends EventHistory>> brokers = new HashMap<>();

    public EventBrokerGroup() {
        for (Class<? extends EventHistory> eventType : EventType.getTypes()) {
            brokers.put(eventType, new EventBroker<>());
        }
    }

    @SuppressWarnings("unchecked")
    public <E extends EventHistory> void push(E event) {
        EventBroker<E> eventBroker = (EventBroker<E>) brokers.get(event.getClass());
        eventBroker.push(event);
    }

    @SuppressWarnings("unchecked")
    public <E extends EventHistory> Optional<E> poll(Class<E> type) {
        EventBroker<E> eventBroker = (EventBroker<E>) brokers.get(type);
        return Optional.ofNullable(eventBroker.poll());
    }

    public Set<Class<? extends EventHistory>> keySet() {
        return this.brokers.keySet();
    }
}
