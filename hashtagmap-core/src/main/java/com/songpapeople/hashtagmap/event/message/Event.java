package com.songpapeople.hashtagmap.event.message;

import java.util.function.Consumer;

public abstract class Event {
    protected final Consumer<Event> eventConsumer;

    public Event(final Consumer<Event> eventConsumer) {
        this.eventConsumer = eventConsumer;
    }

    public void doService() {
        this.eventConsumer.accept(this);
    }
}
