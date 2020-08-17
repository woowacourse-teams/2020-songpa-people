package com.songpapeople.hashtagmap.district.service.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ZoneUpdateDto {
    @NotNull
    private Long zoneId;
    private String districtName;
    private String topLeftLatitude;
    private String topLeftLongitude;
    private String bottomRightLatitude;
    private String bottomRightLongitude;

    @Builder
    public ZoneUpdateDto(@NotNull final Long zoneId, final String districtName, final String topLeftLatitude, final String topLeftLongitude, final String bottomRightLatitude, final String bottomRightLongitude) {
        this.zoneId = zoneId;
        this.districtName = districtName;
        this.topLeftLatitude = topLeftLatitude;
        this.topLeftLongitude = topLeftLongitude;
        this.bottomRightLatitude = bottomRightLatitude;
        this.bottomRightLongitude = bottomRightLongitude;
    }
}
