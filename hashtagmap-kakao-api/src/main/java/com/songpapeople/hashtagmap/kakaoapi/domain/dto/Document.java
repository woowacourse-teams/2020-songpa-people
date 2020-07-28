package com.songpapeople.hashtagmap.kakaoapi.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    @Generated
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

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(id, document.id)
                && Objects.equals(placeName, document.placeName)
                && Objects.equals(categoryName, document.categoryName)
                && Objects.equals(categoryGroupCode, document.categoryGroupCode)
                && Objects.equals(categoryGroupName, document.categoryGroupName)
                && Objects.equals(phone, document.phone)
                && Objects.equals(addressName, document.addressName)
                && Objects.equals(roadAddressName, document.roadAddressName)
                && Objects.equals(x, document.x)
                && Objects.equals(y, document.y)
                && Objects.equals(placeUrl, document.placeUrl)
                && Objects.equals(distance, document.distance);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(id, placeName, categoryName, categoryGroupCode, categoryGroupName, phone, addressName,
                roadAddressName, x, y, placeUrl, distance);
    }
}
