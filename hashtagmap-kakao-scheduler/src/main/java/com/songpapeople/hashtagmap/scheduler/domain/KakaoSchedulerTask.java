package com.songpapeople.hashtagmap.scheduler.domain;

import com.songpapeople.hashtagmap.event.message.KakaoEvent;
import com.songpapeople.hashtagmap.event.service.EventService;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import com.songpapeople.hashtagmap.place.domain.repository.ZoneQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KakaoSchedulerTask {
    private final ZoneQueryRepository zoneQueryRepository;
    private final EventService<KakaoEvent> eventService;

    public KakaoSchedulerTask(final ZoneQueryRepository zoneQueryRepository, final EventService<KakaoEvent> eventService) {
        this.zoneQueryRepository = zoneQueryRepository;
        this.eventService = eventService;
    }

    public void sourceEvent() {
        List<Zone> zones = zoneQueryRepository.findByActivated();

        for (Zone zone : zones) {
            for (Category category : Category.values()) {
                eventService.provide(new KakaoEvent(eventService::collect, eventService::fail, category, zone));
            }
        }
    }

}
