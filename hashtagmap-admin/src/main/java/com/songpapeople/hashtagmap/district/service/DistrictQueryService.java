package com.songpapeople.hashtagmap.district.service;

import com.songpapeople.hashtagmap.district.service.dto.DistrictDto;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictQueryService {
    private final DistrictRepository districtRepository;

    public List<DistrictDto> getAllDistrict() {
        List<District> districts = districtRepository.findAll();
        return DistrictDto.listOf(districts);
    }
}
