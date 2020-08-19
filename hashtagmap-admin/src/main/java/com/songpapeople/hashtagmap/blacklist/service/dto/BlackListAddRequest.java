package com.songpapeople.hashtagmap.blacklist.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BlackListAddRequest {
    private String originName;
    private String replaceName;

    public BlackListAddRequest(String originName, String replaceName) {
        this.originName = originName;
        this.replaceName = replaceName;
    }
}
