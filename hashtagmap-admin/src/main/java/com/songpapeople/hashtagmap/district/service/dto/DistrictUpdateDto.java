package com.songpapeople.hashtagmap.district.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DistrictUpdateDto {
    @NotNull
    private Long districtId;
    @NotNull
    private String districtName;

    public DistrictUpdateDto(@NotNull final Long districtId, @NotNull final String districtName) {
        this.districtId = districtId;
        this.districtName = districtName;
    }
}
