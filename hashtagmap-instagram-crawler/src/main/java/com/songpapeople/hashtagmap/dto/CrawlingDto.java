package com.songpapeople.hashtagmap.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CrawlingDto {
    private final String hashtagName;
    private final Long hashtagCount;
    private final PostDtos postDtos;

    public static CrawlingDto of(String hashtagName, String hashtagCount, PostDtos postDtos) {
        return new CrawlingDto(hashtagName, Long.valueOf(hashtagCount), postDtos);
    }

    public List<PostDto> getPostDtoList() {
        return postDtos.getPostDtos();
    }
}
