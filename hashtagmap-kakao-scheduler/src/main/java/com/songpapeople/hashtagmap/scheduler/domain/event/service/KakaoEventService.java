package com.songpapeople.hashtagmap.scheduler.domain.event.service;

import com.songpapeople.hashtagmap.event.message.Event;
import com.songpapeople.hashtagmap.event.message.KakaoEvent;
import com.songpapeople.hashtagmap.event.model.EventStatus;
import com.songpapeople.hashtagmap.event.model.KakaoEventHistory;
import com.songpapeople.hashtagmap.event.process.EventBrokerGroup;
import com.songpapeople.hashtagmap.event.repository.KakaoEventHistoryRepository;
import com.songpapeople.hashtagmap.event.service.EventService;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Document;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.service.KakaoApiService;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.scheduler.domain.factory.PlaceFactory;
import com.songpapeople.hashtagmap.scheduler.domain.factory.RectFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoEventService implements EventService<KakaoEvent> {
    private final EventBrokerGroup eventBrokerGroup;
    private final KakaoEventHistoryRepository kakaoEventHistoryRepository;
    private final KakaoApiService kakaoApiService;
    private final PlaceRepository placeRepository;

    @Transactional
    public void provide(KakaoEvent event) {
        KakaoEventHistory kakaoEventHistory = KakaoEventHistory.ready(event.getCategory(), event.getZone());
        KakaoEventHistory saveKakaoEventHistory = kakaoEventHistoryRepository.saveAndFlush(kakaoEventHistory);
        event.placeId(saveKakaoEventHistory.getId());
        eventBrokerGroup.push(event);
    }

    @Transactional
    public void collect(Event event) {
        KakaoEvent kakaoEvent = (KakaoEvent) event;
        Rect rect = RectFactory.from(kakaoEvent.getZone());

        KakaoEventHistory kakaoEventHistory = kakaoEventHistoryRepository.findById(kakaoEvent.getEventHistoryId())
                .orElseGet(() -> new KakaoEventHistory(kakaoEvent.getCategory(), kakaoEvent.getZone(), EventStatus.READY));

        try {
            List<KakaoPlaceDto> kakaoPlaceDtos = new ArrayList<>(kakaoApiService.findPlaces(kakaoEvent.getCategoryGroupCode(), rect));
            Set<Document> documents = kakaoPlaceDtos.stream()
                    .flatMap(kakaoPlaceDto -> kakaoPlaceDto.getDocuments().stream())
                    .collect(Collectors.toSet());

            List<Place> places = PlaceFactory.from(documents);
            placeRepository.updateAndInsert(places);
        } catch (Exception e) {
            log.info("event fail id: {}", kakaoEvent.getEventHistoryId());
            kakaoEventHistory.fail();
            kakaoEventHistoryRepository.save(kakaoEventHistory);
            return;
        }
        log.info("event success id: {}", kakaoEvent.getEventHistoryId());
        kakaoEventHistory.success();
        kakaoEventHistoryRepository.save(kakaoEventHistory);
    }

    @Override
    public void restore() {

    }
}
