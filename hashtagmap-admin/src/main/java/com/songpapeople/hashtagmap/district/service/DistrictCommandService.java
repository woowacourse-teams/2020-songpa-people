package com.songpapeople.hashtagmap.district.service;

import com.songpapeople.hashtagmap.district.service.dto.DistrictDeleteDto;
import com.songpapeople.hashtagmap.district.service.dto.DistrictSaveDto;
import com.songpapeople.hashtagmap.district.service.dto.DistrictUpdateDto;
import com.songpapeople.hashtagmap.district.service.dto.ZoneDeleteDto;
import com.songpapeople.hashtagmap.district.service.dto.ZoneSaveDto;
import com.songpapeople.hashtagmap.district.service.dto.ZoneUpdateDto;
import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.CommonExceptionStatus;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import com.songpapeople.hashtagmap.place.domain.repository.DistrictRepository;
import com.songpapeople.hashtagmap.place.domain.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class DistrictCommandService {
    private final DistrictRepository districtRepository;
    private final ZoneRepository zoneRepository;

    @Transactional
    public Long saveDistrict(DistrictSaveDto districtSaveDto) {
        District district = new District(districtSaveDto.getDistrictName());

        boolean present = districtRepository.findByDistrictName(district.getDistrictName()).isPresent();
        if (present) {
            throw new AdminException(
                    CommonExceptionStatus.ALREADY_PERSIST,
                    String.format("이미 존재하는 지역(%s)입니다.", district.getDistrictName())
            );
        }

        district = districtRepository.save(district);
        return district.getId();
    }

    @Transactional
    public void deleteDistricts(DistrictDeleteDto districtDeleteDto) {
        districtRepository.deleteAllByIdIn(districtDeleteDto.getDistrictIds());
    }

    @Transactional
    public void updateDistrict(DistrictUpdateDto districtUpdateDto) {
        District district = districtRepository.findById(districtUpdateDto.getDistrictId())
                .orElseThrow(() -> new AdminException(
                        CommonExceptionStatus.NOT_PERSIST,
                        String.format("존재하지 않는 지역(%d)입니다.", districtUpdateDto.getDistrictId())));

        district.update(districtUpdateDto.getDistrictName());
    }

    @Transactional
    public Long saveZone(ZoneSaveDto zoneSaveDto) {
        District district = districtRepository.findByDistrictName(zoneSaveDto.getDistrictName())
                .orElseThrow(() -> new AdminException(
                        CommonExceptionStatus.NOT_PERSIST,
                        String.format("존재하지 않는 지역이름(%s)입니다.", zoneSaveDto.getDistrictName()))
                );
        Zone zone = Zone.builder()
                .district(district)
                .topLeft(new Point(zoneSaveDto.getTopLeftLatitude(), zoneSaveDto.getTopLeftLongitude()))
                .bottomRight(new Point(zoneSaveDto.getBottomRightLatitude(), zoneSaveDto.getBottomRightLongitude()))
                .isActivated(true)
                .build();
        zone = zoneRepository.save(zone);
        return zone.getId();
    }

    @Transactional
    public void updateZone(final ZoneUpdateDto zoneUpdateDto) {
        Zone zone = zoneRepository.findById(zoneUpdateDto.getZoneId())
                .orElseThrow(() -> new AdminException(
                        CommonExceptionStatus.NOT_PERSIST,
                        String.format("존재하지 않는 구역Id(%d)입니다.", zoneUpdateDto.getZoneId())
                ));

        districtRepository.findByDistrictName(zoneUpdateDto.getDistrictName())
                .ifPresent(zone::changeDistrict);

        zone.updatePosition(zoneUpdateDto.getTopLeftLatitude(),
                zoneUpdateDto.getTopLeftLongitude(),
                zoneUpdateDto.getBottomRightLatitude(),
                zoneUpdateDto.getBottomRightLongitude());
    }

    @Transactional
    public void deleteZones(final ZoneDeleteDto zoneDeleteDto) {
        zoneRepository.deleteAllByIdIn(zoneDeleteDto.getZoneIds());
    }
}
