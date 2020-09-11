package com.songpapeople.hashtagmap.event.service;

import com.songpapeople.hashtagmap.event.model.Event;

public interface EventService {
    void provide(Event event);

    void collect(Event event);
}
