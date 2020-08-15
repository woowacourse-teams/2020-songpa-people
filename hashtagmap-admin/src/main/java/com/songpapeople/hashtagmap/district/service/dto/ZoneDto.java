package com.songpapeople.hashtagmap.district.service.dto;

import com.songpapeople.hashtagmap.place.domain.model.Zone;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ZoneDto {
    private final Long zoneId;
    private final Long districtId;
    private final String districtName;
    private final String topLeftLatitude;
    private final String topLeftLongitude;
    private final String bottomRightLatitude;
    private final String bottomRightLongitude;

    @Builder
    public ZoneDto(final Long zoneId, final Long districtId, final String districtName, final String topLeftLatitude,
                   final String topLeftLongitude, final String bottomRightLatitude, final String bottomRightLongitude) {
        this.zoneId = zoneId;
        this.districtId = districtId;
        this.districtName = districtName;
        this.topLeftLatitude = topLeftLatitude;
        this.topLeftLongitude = topLeftLongitude;
        this.bottomRightLatitude = bottomRightLatitude;
        this.bottomRightLongitude = bottomRightLongitude;
    }

    public static List<ZoneDto> listOf(List<Zone> zones) {
        return zones.stream()
                .map(ZoneDto::of)
                .collect(Collectors.toList());
    }

    public static ZoneDto of(Zone zone) {
        return ZoneDto.builder()
                .zoneId(zone.getId())
                .districtId(zone.getDistrict().getId())
                .districtName(zone.getDistrict().getDistrictName())
                .topLeftLatitude(zone.getMinLatitude())
                .topLeftLongitude(zone.getMinLongitude())
                .bottomRightLatitude(zone.getMaxLatitude())
                .bottomRightLongitude(zone.getMaxLongitude())
                .build();
    }
}
