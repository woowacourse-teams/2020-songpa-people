package com.songpapeople.hashtagmap.kakaoapi.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
public class Meta {
    private Integer totalCount;
    private Integer pageableCount;
    private Boolean isEnd;
    private RegionInfo sameName;

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @Getter
    private class RegionInfo {
        private String[] region;
        private String keyword;
        private String selectedRegion;
    }
}
