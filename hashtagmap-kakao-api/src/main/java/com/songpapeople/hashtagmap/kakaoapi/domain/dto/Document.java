package com.songpapeople.hashtagmap.kakaoapi.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * id: 해당 장소의 업체명에 부여된 고유 ID
 * categoryName: 카테고리 이름
 * categoryGroupCode: 중요 카테고리만 그룹핑한 카테고리 그룹 코드
 * categoryGroupName: 중요 카테고리만 그룹핑한 카테고리 그룹명
 * ex) {
 * "category_name": "의료,건강 > 약국",
 * "category_group_code": "PM9",
 * "category_group_name": "약국",
 * }
 */

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Document {
    private String id;
    private String placeName;
    private String categoryName;
    private String categoryGroupCode;
    private String categoryGroupName;
    private String phone;
    private String addressName;
    private String roadAddressName;
    private String x;
    private String y;
    private String placeUrl;
    private String distance;

    @Builder
    public Document(String id, String placeName, String categoryGroupCode, String roadAddressName,
                    String x, String y, String placeUrl) {
        this.id = id;
        this.placeName = placeName;
        this.categoryGroupCode = categoryGroupCode;
        this.roadAddressName = roadAddressName;
        this.x = x;
        this.y = y;
        this.placeUrl = placeUrl;
    }
}
