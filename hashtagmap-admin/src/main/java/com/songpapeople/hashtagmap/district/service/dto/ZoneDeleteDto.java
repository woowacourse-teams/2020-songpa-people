package com.songpapeople.hashtagmap.district.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ZoneDeleteDto {
    @NotEmpty
    private List<Long> zoneIds;

    public ZoneDeleteDto(final List<Long> zoneIds) {
        this.zoneIds = zoneIds;
    }
}
