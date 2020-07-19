package com.songpapeople.hashtagmap.kakaoapi.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class KakaoPlaceDto {
    private Meta meta;
    private List<Document> documents;
}
