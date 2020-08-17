package com.songpapeople.hashtagmap.member.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginRequest {
    private String nickName;
    private String password;

    public LoginRequest(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
    }
}
