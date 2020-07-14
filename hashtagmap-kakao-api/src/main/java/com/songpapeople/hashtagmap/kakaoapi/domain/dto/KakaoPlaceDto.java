package com.songpapeople.hashtagmap.kakaoapi.domain.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class KakaoPlaceDto {
    private Meta meta;
    private List<Document> documents;
}
