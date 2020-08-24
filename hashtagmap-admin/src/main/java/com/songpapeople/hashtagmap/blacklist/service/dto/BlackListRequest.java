package com.songpapeople.hashtagmap.blacklist.service.dto;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class BlackListRequest {
    @NotNull
    private Long placeId;
    @NotNull
    private String replaceName;

    public BlackListRequest(Long placeId, String replaceName) {
        this.placeId = placeId;
        this.replaceName = replaceName;
    }

    public static BlackList toSkipBlackList(BlackListRequest blackListRequest) {
        return new BlackList(blackListRequest.getPlaceId(), blackListRequest.getReplaceName(), true);
    }
}
