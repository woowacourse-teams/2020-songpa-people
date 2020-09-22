package com.songpapeople.hashtagmap.scheduler.domain;

import com.songpapeople.hashtagmap.event.message.KakaoEvent;
import com.songpapeople.hashtagmap.event.model.EventStatus;
import com.songpapeople.hashtagmap.event.model.KakaoEventHistory;
import com.songpapeople.hashtagmap.event.repository.EventHistoryRepository;
import com.songpapeople.hashtagmap.event.repository.KakaoEventHistoryRepository;
import com.songpapeople.hashtagmap.event.service.EventService;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Document;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.service.KakaoApiService;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import com.songpapeople.hashtagmap.place.domain.repository.DistrictRepository;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.place.domain.repository.ZoneRepository;
import com.songpapeople.hashtagmap.scheduler.domain.event.service.KakaoEventService;
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
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class KakaoSchedulerTaskMockTest {
    @MockBean
    private KakaoApiService kakaoApiService;

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

    @Autowired
    private KakaoEventService kakaoEventService;

    private KakaoSchedulerTask kakaoSchedulerTask;
    private EventService<KakaoEvent> eventEventService;

    @DisplayName("변경된 가게 데이터 저장 시 해당 가게 정보 업데이트")
    @Test
    void sourceEvent() throws InterruptedException {
        //given
        District district = districtRepository.save(new District("서울시 송파구"));
        Point point = new Point("34", "124");
        Zone zone = zoneRepository.save(new Zone(point, point, district, true));
        Place beforePlace = placeRepository.save(getPlace("33", "127"));

        when(kakaoApiService.findPlaces(any(), any())).thenReturn(getKakaoPlaceDtos("34", "124"));

        //event consumer가 일을 끝낼때까지 기다리기 위한 CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(1);
        eventEventService = new EventService<KakaoEvent>() {
            @Override
            public void provide(final KakaoEvent event) {
                kakaoEventService.provide(event);
            }

            @Override
            public void collect(final KakaoEvent event) {
                kakaoEventService.collect(event);
                countDownLatch.countDown();
            }

            @Override
            public void fail(final KakaoEvent event) {

            }
        };
        kakaoSchedulerTask = new KakaoSchedulerTask(zoneRepository, eventEventService);

        //when
        kakaoSchedulerTask.sourceEvent();
        countDownLatch.await();

        //then
        Place changedPlace = placeRepository.findByPlaceName(beforePlace.getPlaceName()).get(0);
        assertAll("place 업데이트 확인",
                () -> assertThat(changedPlace.getLocation().getPoint().getLatitude()).isEqualTo("34"),
                () -> assertThat(changedPlace.getLocation().getPoint().getLongitude()).isEqualTo("124")
        );

        KakaoEventHistory kakaoEventHistory = eventHistoryRepository.findAll().get(0);
        assertAll("event 성공처리 확인",
                () -> assertThat(kakaoEventHistory.getEventStatus()).isEqualTo(EventStatus.SUCCESS)
        );
    }

    private List<KakaoPlaceDto> getKakaoPlaceDtos(final String latitude, final String longitude) {
        return Arrays.asList(
                new KakaoPlaceDto(null,
                        Arrays.asList(
                                Document.builder()
                                        .id("124")
                                        .categoryGroupCode("CE7")
                                        .placeName("mahogani")
                                        .placeUrl("http://mahogani.com")
                                        .roadAddressName("서울시 송파구")
                                        .latitude(latitude)
                                        .longitude(longitude)
                                        .build()
                        ))
        );
    }

    private Place getPlace(final String latitude, final String longitude) {
        return Place.builder()
                .kakaoId("124")
                .category(Category.CAFE)
                .location(new Location(new Point(latitude, longitude), "서울시 송파구"))
                .placeName("mahogani")
                .placeUrl("http://mahogani.com")
                .build();
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
