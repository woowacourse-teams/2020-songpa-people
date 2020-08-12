package com.songpapeople.hashtagmap.district.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DistrictDeleteDto {
    @NotEmpty
    private List<Long> districtIds;

    public DistrictDeleteDto(final List<Long> districtIds) {
        this.districtIds = districtIds;
    }
}
