package com.songpapeople.hashtagmap.instagram.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class InstagramBatchDto {
    private final Long instagramId;
    private final Long placeId;

    @QueryProjection
    public InstagramBatchDto(Long instagramId, Long placeId) {
        this.instagramId = instagramId;
        this.placeId = placeId;
    }
}
