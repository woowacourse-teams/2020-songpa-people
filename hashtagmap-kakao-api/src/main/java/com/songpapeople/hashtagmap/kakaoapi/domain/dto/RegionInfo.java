package com.songpapeople.hashtagmap.kakaoapi.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * region: 질의어에서 인식된 지역의 리스트
 * ex) '중앙로 맛집' 에서 중앙로에 해당하는 지역 리스트
 * keyword: 질의어에서 지역 정보를 제외한 키워드
 * ex) '중앙로 맛집' 에서 '맛집'
 * selectedRegion: 인식된 지역 리스트 중, 현재 검색에 사용된 지역 정보
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RegionInfo {
    private String[] region;
    private String keyword;
    private String selectedRegion;
}
