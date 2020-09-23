package com.songpapeople.hashtagmap.scheduler.domain.event.service;

import com.songpapeople.hashtagmap.event.message.KakaoEvent;
import com.songpapeople.hashtagmap.event.model.EventStatus;
import com.songpapeople.hashtagmap.event.model.KakaoEventHistory;
import com.songpapeople.hashtagmap.event.process.EventBrokerGroup;
import com.songpapeople.hashtagmap.event.repository.EventHistoryRepository;
import com.songpapeople.hashtagmap.event.repository.KakaoEventHistoryRepository;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Document;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.service.KakaoApiService;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import com.songpapeople.hashtagmap.place.domain.repository.DistrictRepository;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.place.domain.repository.ZoneRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class KakaoEventServiceTest {
    @MockBean
    private EventBrokerGroup eventBrokerGroup;

    @MockBean
    private KakaoApiService kakaoApiService;

    @Autowired
    private KakaoEventService kakaoEventService;

    @Autowired
    private EventHistoryRepository<KakaoEventHistory> eventHistoryRepository;

    @Autowired
    private KakaoEventHistoryRepository kakaoEventHistoryRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @DisplayName("이벤트를 READY 상태로 저장한다.")
    @Test
    void provide() {
        //given
        KakaoEvent kakaoEvent = getKakaoEvent(
                (event -> {
                }),
                (event -> {
                }));

        doNothing().when(eventBrokerGroup).push(any());

        //when
        kakaoEventService.provide(kakaoEvent);

        //then
        KakaoEventHistory kakaoEventHistory = eventHistoryRepository.findAll().get(0);
        assertThat(kakaoEventHistory.getEventStatus()).isEqualTo(EventStatus.READY);
    }

    private KakaoEvent getKakaoEvent(Consumer<KakaoEvent> eventConsumer, Consumer<KakaoEvent> failEventConsumer) {
        District district = new District("서울시 강남구");
        district = districtRepository.save(district);
        Point point = new Point("34", "124");
        Zone zone = new Zone(point, point, district, true);
        zone = zoneRepository.save(zone);
        return new KakaoEvent(
                eventConsumer,
                failEventConsumer,
                Category.CAFE, zone);
    }

    @DisplayName("READY 상태였던 이벤트를 성공 시킨다.")
    @Test
    void collect() {
        //given
        KakaoEvent kakaoEvent = getKakaoEvent(
                (event -> {
                }),
                (event -> {
                })
        );
        KakaoEventHistory kakaoEventHistory = kakaoEventHistoryRepository.save(KakaoEventHistory.ready(kakaoEvent.getCategory(), kakaoEvent.getZone()));
        kakaoEvent.placeId(kakaoEventHistory.getId());

        List<KakaoPlaceDto> kakaoPlaceDtos = getKakaoPlaceDtos();
        when(kakaoApiService.findPlaces(any(), any())).thenReturn(kakaoPlaceDtos);

        //when
        kakaoEventService.collect(kakaoEvent);

        //then
        KakaoEventHistory findHistory = eventHistoryRepository.findAll().get(0);
        assertThat(findHistory.getEventStatus()).isEqualTo(EventStatus.SUCCESS);
    }

    @DisplayName("존재하지 않는 이벤트를 실행하는 경우 Exception 발생")
    @Test
    void collectNotFoundIdException() {
        KakaoEvent kakaoEvent = getKakaoEvent(
                (event -> {
                }),
                (event -> {
                })
        );
        kakaoEvent.placeId(-100L);

        assertThatThrownBy(() -> kakaoEventService.collect(kakaoEvent))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("저장되지 않은 이벤트%s 입니다.", kakaoEvent.getEventHistoryId());
    }

    @DisplayName("수집과정에 문제가 발생한 경우 이벤트는 fail로 저장된다.")
    @Test
    void collectFailCauseThrowException() {
        //given
        KakaoEvent kakaoEvent = getKakaoEvent(
                (event -> {
                }),
                (event -> {
                }));
        KakaoEventHistory kakaoEventHistory = kakaoEventHistoryRepository.save(KakaoEventHistory.ready(kakaoEvent.getCategory(), kakaoEvent.getZone()));
        kakaoEvent.placeId(kakaoEventHistory.getId());

        //when
        kakaoEventService.fail(kakaoEvent);

        //then
        KakaoEventHistory failHistory = eventHistoryRepository.findAll().get(0);
        assertThat(failHistory.getEventStatus()).isEqualTo(EventStatus.FAIL);
    }

    @DisplayName("존재하지 않는 history를 fail처리 하려는 경우 exception 발생")
    @Test
    void collectFailNotFoundCauseThrowException() {
        //given
        KakaoEvent kakaoEvent = getKakaoEvent(
                (event -> {
                }),
                (event -> {
                }));
        kakaoEvent.placeId(-1L);

        //then
        assertThatThrownBy(() -> kakaoEventService.fail(kakaoEvent))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("저장되지 않은 이벤트%s 입니다.", -1L);

    }

    private List<KakaoPlaceDto> getKakaoPlaceDtos() {
        return Arrays.asList(
                new KakaoPlaceDto(null,
                        Arrays.asList(
                                Document.builder()
                                        .id("123")
                                        .categoryGroupCode("CE7")
                                        .placeName("woowacourse")
                                        .placeUrl("http://woowacourse.com")
                                        .roadAddressName("서울시 송파구")
                                        .latitude("33")
                                        .longitude("127")
                                        .build(),
                                Document.builder()
                                        .id("124")
                                        .categoryGroupCode("CE7")
                                        .placeName("mahogani")
                                        .placeUrl("http://mahogani.com")
                                        .roadAddressName("서울시 송파구")
                                        .latitude("33.1")
                                        .longitude("127.1")
                                        .build()
                        ))
        );
    }

    @AfterEach
    void tearDown() {
        kakaoEventHistoryRepository.deleteAll();
        eventHistoryRepository.deleteAll();
        zoneRepository.deleteAll();
        districtRepository.deleteAll();
        placeRepository.deleteAll();
    }
}