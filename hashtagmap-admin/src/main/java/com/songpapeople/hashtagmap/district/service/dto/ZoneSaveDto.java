package com.songpapeople.hashtagmap.district.service.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ZoneSaveDto {
    @NotNull
    private String districtName;
    @NotBlank
    private String topLeftLatitude;
    @NotBlank
    private String topLeftLongitude;
    @NotBlank
    private String bottomRightLatitude;
    @NotBlank
    private String bottomRightLongitude;

    @Builder
    public ZoneSaveDto(@NotNull final String districtName, @NotBlank final String topLeftLatitude, @NotBlank final String topLeftLongitude, @NotBlank final String bottomRightLatitude, @NotBlank final String bottomRightLongitude) {
        this.districtName = districtName;
        this.topLeftLatitude = topLeftLatitude;
        this.topLeftLongitude = topLeftLongitude;
        this.bottomRightLatitude = bottomRightLatitude;
        this.bottomRightLongitude = bottomRightLongitude;
    }
}
