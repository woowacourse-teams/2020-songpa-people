package com.songpapeople.hashtagmap.district.service;

import com.songpapeople.hashtagmap.district.service.dto.DistrictDto;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.repository.DistrictRepository;
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

    @AfterEach
    void tearDown() {
        districtRepository.deleteAll();
    }
}