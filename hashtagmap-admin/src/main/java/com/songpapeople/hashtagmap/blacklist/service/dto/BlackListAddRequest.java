package com.songpapeople.hashtagmap.blacklist.service.dto;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BlackListAddRequest {
    private String kakaoId;
    private String replaceName;

    public BlackListAddRequest(String kakaoId, String replaceName) {
        this.kakaoId = kakaoId;
        this.replaceName = replaceName;
    }

    public static BlackList toBlackList(BlackListAddRequest blackListRequest) {
        return new BlackList(blackListRequest.kakaoId, blackListRequest.replaceName);
    }
}
