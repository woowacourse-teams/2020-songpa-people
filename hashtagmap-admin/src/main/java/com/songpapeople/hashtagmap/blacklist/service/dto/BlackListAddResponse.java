package com.songpapeople.hashtagmap.blacklist.service.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BlackListAddResponse {
    private Long placeId;
    private String replaceName;
    private Long hashtagCount;

    private BlackListAddResponse(Long placeId, String replaceName, Long hashtagCount) {
        this.placeId = placeId;
        this.replaceName = replaceName;
        this.hashtagCount = hashtagCount;
    }

    public static BlackListAddResponse of(Instagram instagram) {
        return new BlackListAddResponse(instagram.getPlaceId()
                , instagram.getHashtagName()
                , instagram.getHashtagCount());
    }
}
