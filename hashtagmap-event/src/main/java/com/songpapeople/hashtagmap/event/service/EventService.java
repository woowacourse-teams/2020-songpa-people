package com.songpapeople.hashtagmap.event.service;

import com.songpapeople.hashtagmap.event.message.Event;

public interface EventService<E extends Event> {

    void provide(E event);

    void fail(E event);

    void collect(E event);
}
