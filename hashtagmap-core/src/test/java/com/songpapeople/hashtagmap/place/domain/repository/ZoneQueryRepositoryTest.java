package com.songpapeople.hashtagmap.place.domain.repository;

import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: 2020/07/24 @DataJpaTest를 활용해서 테스트 코드 변경해보기
@SpringBootTest
public class ZoneQueryRepositoryTest {
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private ZoneQueryRepository zoneQueryRepository;

    @DisplayName("활성화된 Zone만 조회")
    @Test
    public void findByActivateTest() {
        District district = new District("서울시 송파구");
        districtRepository.save(district);

        Zone zone1 = Zone.builder()
                .topLeft(new Point("33", "130"))
                .bottomRight(new Point("35", "128"))
                .district(district)
                .isActivated(true)
                .build();
        Zone zone2 = Zone.builder()
                .topLeft(new Point("33", "130"))
                .bottomRight(new Point("35", "128"))
                .district(district)
                .isActivated(true)
                .build();
        Zone zone3 = Zone.builder()
                .topLeft(new Point("33", "130"))
                .bottomRight(new Point("35", "128"))
                .district(district)
                .isActivated(false)
                .build();
        zoneRepository.saveAll(Arrays.asList(zone1, zone2, zone3));

        List<Zone> activatedZones = zoneQueryRepository.findByActivated();
        assertThat(activatedZones).hasSize(2);
    }

    @AfterEach
    void tearDown() {
        zoneRepository.deleteAll();
        districtRepository.deleteAll();
    }
}
