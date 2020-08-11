package com.songpapeople.hashtagmap.district.service;

import com.songpapeople.hashtagmap.district.service.dto.DistrictSaveDto;
import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.repository.DistrictRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class DistrictCommandServiceTest {
    private static final String SEOUL_SONGPA = "서울 송파구";

    @Autowired
    private DistrictCommandService districtCommandService;

    @Autowired
    private DistrictRepository districtRepository;

    @DisplayName("정상 저장 테스트")
    @Test
    void save() {
        //given
        DistrictSaveDto districtSaveDto = new DistrictSaveDto(SEOUL_SONGPA);

        //when
        Long saveId = districtCommandService.saveDistrict(districtSaveDto);

        //then
        District district = districtRepository.findById(saveId).get();
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
                .hasMessage("%s : 이미 존재하는 지역 이름입니다.", SEOUL_SONGPA);
    }

    @AfterEach
    void tearDown() {
        districtRepository.deleteAll();
    }
}