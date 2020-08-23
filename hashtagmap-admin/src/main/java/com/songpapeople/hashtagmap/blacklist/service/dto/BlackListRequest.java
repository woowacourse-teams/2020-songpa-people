package com.songpapeople.hashtagmap.blacklist.service.dto;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BlackListRequest {
    private Long placeId;
    private String replaceName;

    public BlackListRequest(Long placeId, String replaceName) {
        this.placeId = placeId;
        this.replaceName = replaceName;
    }

    public static BlackList toBlackList(BlackListRequest blackListRequest) {
        return new BlackList(blackListRequest.placeId, blackListRequest.replaceName);
    }
}
