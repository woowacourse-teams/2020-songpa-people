package com.songpapeople.hashtagmap.district.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DistrictSaveDto {
    @NotBlank
    private String districtName;

    public DistrictSaveDto(final String districtName) {
        this.districtName = districtName;
    }
}
