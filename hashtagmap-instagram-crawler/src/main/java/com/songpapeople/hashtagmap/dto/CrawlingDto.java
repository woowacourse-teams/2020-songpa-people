package com.songpapeople.hashtagmap.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CrawlingDto {
    private final String placeName;
    private final Long hashtagCount;
    private final PostDtos postDtos;

    public static CrawlingDto of(String placeName, String hashtagCount, PostDtos postDtos) {
        return new CrawlingDto(placeName, Long.valueOf(hashtagCount), postDtos);
    }
}
