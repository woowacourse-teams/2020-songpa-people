package com.songpapeople.hashtagmap.district.service;

import com.songpapeople.hashtagmap.district.service.dto.DistrictDto;
import com.songpapeople.hashtagmap.district.service.dto.ZoneDto;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import com.songpapeople.hashtagmap.place.domain.repository.DistrictRepository;
import com.songpapeople.hashtagmap.place.domain.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DistrictQueryService {
    private final DistrictRepository districtRepository;
    private final ZoneRepository zoneRepository;

    public List<DistrictDto> getAllDistrict() {
        List<District> districts = districtRepository.findAll();
        return DistrictDto.listOf(districts);
    }

    public List<String> getAllDistrictName() {
        List<District> districts = districtRepository.findAll();
        return districts.stream()
                .map(District::getDistrictName)
                .collect(Collectors.toList());
    }

    public List<ZoneDto> getAllZone() {
        List<Zone> zones = zoneRepository.findAll();
        return ZoneDto.listOf(zones);
    }
}
