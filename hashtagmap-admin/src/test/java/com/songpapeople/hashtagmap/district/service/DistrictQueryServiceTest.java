package com.songpapeople.hashtagmap.district.service;

import com.songpapeople.hashtagmap.district.service.dto.DistrictDto;
import com.songpapeople.hashtagmap.district.service.dto.ZoneDto;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import com.songpapeople.hashtagmap.place.domain.repository.DistrictRepository;
import com.songpapeople.hashtagmap.place.domain.repository.ZoneRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DistrictQueryServiceTest {

    @Autowired
    private DistrictQueryService districtQueryService;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @DisplayName("저장되어 있는 District 정보를 dto로 변환")
    @Test
    void getAllDistrict() {
        //given
        districtRepository.save(new District("서울 송파구"));
        districtRepository.save(new District("서울 강남구"));

        //when
        List<DistrictDto> districtDtos = districtQueryService.getAllDistrict();

        //then
        assertThat(districtDtos).hasSize(2);
    }

    @DisplayName("저장된 District의 이름만을 반환한다.")
    @Test
    void getAllDistrictName() {
        //given
        districtRepository.save(new District("서울 송파구"));
        districtRepository.save(new District("서울 강남구"));

        //when
        List<String> names = districtQueryService.getAllDistrictName();

        //then
        assertThat(names).contains("서울 송파구", "서울 강남구");
    }

    @DisplayName("전체 Zone 정보를 가져온다.")
    @Test
    void getAllZone() {
        //given
        District district = districtRepository.save(new District("서울 송파구"));
        zoneRepository.save(new Zone(new Point("37.4862834222378", "127.099360450322"), new Point("37.4862834222378", "127.099360450322"), district, true));
        zoneRepository.save(new Zone(new Point("37.4862834222378", "127.099360450322"), new Point("37.4862834222378", "127.099360450322"), district, true));

        //when
        List<ZoneDto> allZone = districtQueryService.getAllZone();

        //then
        assertThat(allZone).hasSize(2);
    }

    @AfterEach
    void tearDown() {
        zoneRepository.deleteAll();
        districtRepository.deleteAll();
    }
}