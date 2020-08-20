package com.songpapeople.hashtagmap.blacklist.service.dto;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BlackListAddRequest {
    private Long placeId;
    private String replaceName;

    public BlackListAddRequest(Long placeId, String replaceName) {
        this.placeId = placeId;
        this.replaceName = replaceName;
    }

    public static BlackList toBlackList(BlackListAddRequest blackListRequest) {
        return new BlackList(blackListRequest.placeId, blackListRequest.replaceName);
    }
}
