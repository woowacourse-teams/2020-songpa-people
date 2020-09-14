package com.songpapeople.hashtagmap.scheduler.domain;

import com.songpapeople.hashtagmap.event.model.KakaoEventHistory;
import com.songpapeople.hashtagmap.event.repository.EventHistoryRepository;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import com.songpapeople.hashtagmap.place.domain.repository.DistrictRepository;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.place.domain.repository.ZoneRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest
public class KakaoSchedulerTaskTest {
    @Autowired
    private KakaoSchedulerTask kakaoSchedulerTask;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private EventHistoryRepository<KakaoEventHistory> eventHistoryRepository;

    @BeforeEach
    private void setUp() {
        District district = new District("서울시 송파구");
        districtRepository.save(district);

        Zone zone = Zone.builder()
                .topLeft(new Point("37.508647", "127.125925"))
                .bottomRight(new Point("37.510647", "127.123925"))
                .district(district)
                .isActivated(true)
                .build();
        zoneRepository.save(zone);
    }

    @DisplayName("Task Test - 실제 Kakao Api 호출")
    @Test
    void sourceEvent() {
        kakaoSchedulerTask.sourceEvent();

        //then
        List<KakaoEventHistory> all = eventHistoryRepository.findAll();
        assertThat(all).isNotEmpty();
    }

    @AfterEach
    private void tearDown() {
        placeRepository.deleteAll();
        zoneRepository.deleteAll();
        districtRepository.deleteAll();

    }
}
