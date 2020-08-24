package com.songpapeople.hashtagmap.blacklist.service.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BlackListResponse {
    private String kakaoId;
    private String replaceName;
    private Long hashtagCount;

    public static BlackListResponse of(Instagram instagram) {
        return new BlackListResponse(instagram.getKakaoId()
                , instagram.getHashtagName()
                , instagram.getHashtagCount());
    }

    private BlackListResponse(String kakaoId, String replaceName, Long hashtagCount) {
        this.kakaoId = kakaoId;
        this.replaceName = replaceName;
        this.hashtagCount = hashtagCount;
    }
}
