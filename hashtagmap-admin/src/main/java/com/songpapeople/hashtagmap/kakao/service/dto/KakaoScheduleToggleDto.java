package com.songpapeople.hashtagmap.kakao.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoScheduleToggleDto {
    @NotBlank
    private String name;

    public KakaoScheduleToggleDto(@NotNull final String name) {
        this.name = name;
    }
}
