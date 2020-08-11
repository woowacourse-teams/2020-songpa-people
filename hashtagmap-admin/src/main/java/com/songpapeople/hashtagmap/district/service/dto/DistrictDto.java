package com.songpapeople.hashtagmap.district.service.dto;

import com.songpapeople.hashtagmap.place.domain.model.District;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DistrictDto {
    private final Long districtId;
    private final String districtName;
    private final LocalDateTime createdTime;
    private final LocalDateTime updatedTime;
    private final String memberName;

    @Builder
    public DistrictDto(final Long districtId, final String districtName, final LocalDateTime createdTime, final LocalDateTime updatedTime, final String memberName) {
        this.districtId = districtId;
        this.districtName = districtName;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.memberName = memberName;
    }

    public static List<DistrictDto> listOf(List<District> districts) {
        return districts.stream()
                .map(DistrictDto::of)
                .collect(Collectors.toList());
    }

    private static DistrictDto of(District district) {
        return DistrictDto.builder()
                .createdTime(district.getCreatedDate())
                .updatedTime(district.getModifiedDate())
                .districtId(district.getId())
                .districtName(district.getDistrictName())
                .build();
    }
}
