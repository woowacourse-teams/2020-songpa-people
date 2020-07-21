package com.songpapeople.hashtagmap.kakaoapi.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * meta: Response에 대한 Meta 데이터
 * documents: 검색한 지역에 포함된 가게의 정보
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class KakaoPlaceDto {
    private Meta meta;
    private List<Document> documents;

    public int getTotalCount() {
        return this.meta.getTotalCount();
    }

    public int getPageableCount() {
        return this.meta.getPageableCount();
    }
}
