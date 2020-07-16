package com.songpapeople.hashtagmap.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CrawlingDto {
    private final String placeName;
    private final Long hashtagCount;
    private final PostDtos postDtos;

    public static CrawlingDto of(String placeName, String hashtagCount, PostDtos postDtos) {
        return new CrawlingDto(placeName, Long.valueOf(hashtagCount), postDtos);
    }
}
