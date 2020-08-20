package com.songpapeople.hashtagmap.blacklist.service.dto;

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
}
