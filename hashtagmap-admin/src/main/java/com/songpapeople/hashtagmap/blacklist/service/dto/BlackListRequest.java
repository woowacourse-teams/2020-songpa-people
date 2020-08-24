package com.songpapeople.hashtagmap.blacklist.service.dto;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class BlackListRequest {
    @NotNull
    private String kakaoId;
    @NotNull
    private String replaceName;

    public static BlackList toSkipBlackList(BlackListRequest blackListRequest) {
        return new BlackList(blackListRequest.getKakaoId(), blackListRequest.getReplaceName(), true);
    }

    public BlackListRequest(String kakaoId, String replaceName) {
        this.kakaoId = kakaoId;
        this.replaceName = replaceName;
    }
}
