package com.songpapeople.hashtagmap.event.process;

import com.songpapeople.hashtagmap.event.message.Event;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventBroker<E extends Event> {
    private final BlockingQueue<E> eventQueue = new LinkedBlockingQueue<>();

    public boolean isEmpty() {
        return eventQueue.isEmpty();
    }

    public void push(E event) {
        eventQueue.add(event);
    }

    public E poll() {
        return eventQueue.poll();
    }
}
