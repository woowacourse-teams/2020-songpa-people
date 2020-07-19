package com.songpapeople.hashtagmap.kakaoapi.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Meta {
    private Integer totalCount;
    private Integer pageableCount;
    private Boolean isEnd;
    private RegionInfo sameName;

    @Builder
    public Meta(Integer totalCount, Integer pageableCount, Boolean isEnd, RegionInfo sameName) {
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.isEnd = isEnd;
        this.sameName = sameName;
    }
}
