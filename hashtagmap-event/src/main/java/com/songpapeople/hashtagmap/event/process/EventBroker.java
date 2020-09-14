package com.songpapeople.hashtagmap.event.process;

import com.songpapeople.hashtagmap.event.message.Event;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class EventBroker<E extends Event> {
    private final BlockingQueue<E> eventQueue = new LinkedBlockingQueue<>();

    public void push(E event) {
        eventQueue.add(event);
    }

    public E poll() {
        return eventQueue.poll();
    }

}
