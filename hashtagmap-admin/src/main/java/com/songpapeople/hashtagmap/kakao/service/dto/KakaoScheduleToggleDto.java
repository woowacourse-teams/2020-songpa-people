package com.songpapeople.hashtagmap.kakao.service.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class KakaoScheduleToggleDto {
    @NotNull
    private String target;

    public KakaoScheduleToggleDto(@NotNull final String target) {
        this.target = target;
    }
}
