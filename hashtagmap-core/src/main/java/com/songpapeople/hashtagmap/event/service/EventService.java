package com.songpapeople.hashtagmap.event.service;

import com.songpapeople.hashtagmap.event.model.EventHistory;

public interface EventService {
    void provide(EventHistory eventHistory);

    void collect(EventHistory eventHistory);
}
