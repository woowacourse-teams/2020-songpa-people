package com.songpapeople.hashtagmap.dto;

import lombok.Getter;

@Getter
public class CrawlingDto {
    private final String placeName;
    private final Long hashtagCount;
    private final PostDtos postDtos;

    private CrawlingDto(String placeName, Long hashtagCount, PostDtos postDtos) {
        this.placeName = placeName;
        this.hashtagCount = hashtagCount;
        this.postDtos = postDtos;
    }

    public static CrawlingDto of(String placeName, String hashtagCount, PostDtos postDtos) {
        return new CrawlingDto(placeName, Long.valueOf(hashtagCount), postDtos);
    }
}
