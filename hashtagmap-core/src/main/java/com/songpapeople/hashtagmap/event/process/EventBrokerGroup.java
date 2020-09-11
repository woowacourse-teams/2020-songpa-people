package com.songpapeople.hashtagmap.event.process;

import com.songpapeople.hashtagmap.event.model.Event;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class EventBrokerGroup {
    private final Map<Class<? extends Event>, EventBroker<? extends Event>> brokers = new HashMap<>();

    public EventBrokerGroup() {
        for (Class<? extends Event> eventType : EventType.getTypes()) {
            brokers.put(eventType, new EventBroker<>());
        }
    }

    @SuppressWarnings("unchecked")
    public <E extends Event> void push(E event) {
        EventBroker<E> eventBroker = (EventBroker<E>) brokers.get(event.getClass());
        eventBroker.push(event);
    }

    @SuppressWarnings("unchecked")
    public <E extends Event> Optional<E> poll(Class<E> type) {
        EventBroker<E> eventBroker = (EventBroker<E>) brokers.get(type);
        return Optional.ofNullable(eventBroker.poll());
    }

    public Set<Class<? extends Event>> keySet() {
        return this.brokers.keySet();
    }
}
