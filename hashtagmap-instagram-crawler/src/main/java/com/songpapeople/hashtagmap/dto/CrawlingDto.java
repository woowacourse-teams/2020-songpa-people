package com.songpapeople.hashtagmap.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class CrawlingDto {
    private final String placeName;
    private final Long hashtagCount;
    private final List<PostDto> postDtos;

    private CrawlingDto(String placeName, Long hashtagCount, List<PostDto> postDtos) {
        this.placeName = placeName;
        this.hashtagCount = hashtagCount;
        this.postDtos = postDtos;
    }

    public static CrawlingDto of(String placeName, String hashtagCount, List<PostDto> postDtos) {
        return new CrawlingDto(placeName, Long.valueOf(hashtagCount), postDtos);
    }
}
