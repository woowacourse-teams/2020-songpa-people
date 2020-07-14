package com.songpapeople.hashtagmap.kakaoapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class KakaoPlaceDto {
    private Meta meta;
    private List<Document> documents;
}
