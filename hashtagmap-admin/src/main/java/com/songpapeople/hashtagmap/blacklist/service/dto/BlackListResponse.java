package com.songpapeople.hashtagmap.blacklist.service.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BlackListResponse {
    private Long placeId;
    private String replaceName;
    private Long hashtagCount;

    private BlackListResponse(Long placeId, String replaceName, Long hashtagCount) {
        this.placeId = placeId;
        this.replaceName = replaceName;
        this.hashtagCount = hashtagCount;
    }

    public static BlackListResponse of(Instagram instagram) {
        return new BlackListResponse(instagram.getPlaceId()
                , instagram.getHashtagName()
                , instagram.getHashtagCount());
    }
}
