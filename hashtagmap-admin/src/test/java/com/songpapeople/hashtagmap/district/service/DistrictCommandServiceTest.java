package com.songpapeople.hashtagmap.district.service;

import com.songpapeople.hashtagmap.district.service.dto.DistrictDeleteDto;
import com.songpapeople.hashtagmap.district.service.dto.DistrictSaveDto;
import com.songpapeople.hashtagmap.district.service.dto.DistrictUpdateDto;
import com.songpapeople.hashtagmap.district.service.dto.ZoneSaveDto;
import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import com.songpapeople.hashtagmap.place.domain.repository.DistrictRepository;
import com.songpapeople.hashtagmap.place.domain.repository.ZoneRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class DistrictCommandServiceTest {
    private static final String SEOUL_SONGPA = "서울 송파구";

    @Autowired
    private DistrictCommandService districtCommandService;

    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ZoneRepository zoneRepository;

    @DisplayName("정상 저장 테스트")
    @Test
    void save() {
        //given
        DistrictSaveDto districtSaveDto = new DistrictSaveDto(SEOUL_SONGPA);

        //when
        Long saveId = districtCommandService.saveDistrict(districtSaveDto);

        //then
        District district = districtRepository.findById(saveId).orElseThrow(RuntimeException::new);
        assertThat(district.getDistrictName()).isEqualTo(SEOUL_SONGPA);
    }

    @DisplayName("이미 존재하는 지역을 저장하려는 경우 Exception 발생")
    @Test
    void saveException() {
        //given
        districtRepository.save(new District(SEOUL_SONGPA));

        DistrictSaveDto districtSaveDto = new DistrictSaveDto(SEOUL_SONGPA);

        //then
        assertThatThrownBy(() -> districtCommandService.saveDistrict(districtSaveDto))
                .isInstanceOf(AdminException.class)
                .hasMessage("이미 존재하는 지역(%s)입니다.", SEOUL_SONGPA);
    }

    @DisplayName("새로운 Zone을 저장한다.")
    @Test
    void saveZone() {
        //given
        districtRepository.save(new District(SEOUL_SONGPA));
        ZoneSaveDto zoneSaveDto = ZoneSaveDto.builder()
                .districtName(SEOUL_SONGPA)
                .topLeftLatitude("37.542124")
                .topLeftLongitude("127.069733")
                .bottomRightLatitude("37.505974")
                .bottomRightLongitude("127.069733")
                .build();

        //when
        Long saveZoneId = districtCommandService.saveZone(zoneSaveDto);

        //then
        Zone zone = zoneRepository.findById(saveZoneId).orElseThrow(RuntimeException::new);
        assertThat(zone.getMinLatitude()).isEqualTo("37.542124");
        assertThat(zone.getMinLongitude()).isEqualTo("127.069733");
        assertThat(zone.getMaxLatitude()).isEqualTo("37.505974");
        assertThat(zone.getMaxLongitude()).isEqualTo("127.069733");
    }

    @DisplayName("선택된 District를 삭제한다.")
    @Test
    void deleteDistricts() {
        //given
        District district1 = districtRepository.save(new District("서울 송파구"));
        District district2 = districtRepository.save(new District("서울 강남구"));
        District district3 = districtRepository.save(new District("서울 서초구"));

        DistrictDeleteDto districtDeleteDto = new DistrictDeleteDto(Arrays.asList(district1.getId(), district2.getId()));

        //when
        districtCommandService.deleteDistricts(districtDeleteDto);

        //then
        boolean present1 = districtRepository.findById(district1.getId()).isPresent();
        boolean present2 = districtRepository.findById(district2.getId()).isPresent();
        List<District> districts = districtRepository.findAll();
        assertThat(present1).isFalse();
        assertThat(present2).isFalse();
        assertThat(districts).hasSize(1);
    }

    @DisplayName("지역 업데이트")
    @Test
    void updateDistrict() {
        //given
        District district = districtRepository.save(new District(SEOUL_SONGPA));
        DistrictUpdateDto districtUpdateDto = new DistrictUpdateDto(district.getId(), "서울 강남구");

        //when
        districtCommandService.updateDistrict(districtUpdateDto);

        //then
        District findDistrict = districtRepository.findById(district.getId()).orElseThrow(RuntimeException::new);
        assertThat(findDistrict.getDistrictName()).isEqualTo("서울 강남구");
    }

    @AfterEach
    void tearDown() {
        zoneRepository.deleteAll();
        districtRepository.deleteAll();
    }
}