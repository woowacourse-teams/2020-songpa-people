package com.songpapeople.hashtagmap.event.repository;

import com.songpapeople.hashtagmap.event.model.EventStatus;
import com.songpapeople.hashtagmap.event.model.KakaoEventHistory;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import com.songpapeople.hashtagmap.place.domain.repository.DistrictRepository;
import com.songpapeople.hashtagmap.place.domain.repository.ZoneRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EventHistoryRepositoryTest {

    @Autowired
    private KakaoEventHistoryRepository kakaoEventHistoryRepository;

    @Autowired
    private EventHistoryRepository<KakaoEventHistory> eventHistoryRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @DisplayName("BaseEntity - EventHistory - KakaoEventHistory 상속 테스트")
    @Test
    void name() {
        //given
        District district = new District("1");
        district = districtRepository.save(district);
        Zone zone = new Zone(new Point("33", "124"), new Point("33", "124"), district, true);
        zone = zoneRepository.save(zone);
        KakaoEventHistory kakaoEventHistory = new KakaoEventHistory(Category.CAFE, zone, EventStatus.READY);
        //when
        kakaoEventHistoryRepository.save(kakaoEventHistory);

        //then
        List<KakaoEventHistory> all = eventHistoryRepository.findAll();
        List<KakaoEventHistory> all1 = kakaoEventHistoryRepository.findAll();

        assertThat(all).hasSize(1);
        assertThat(all1).hasSize(1);
    }

}