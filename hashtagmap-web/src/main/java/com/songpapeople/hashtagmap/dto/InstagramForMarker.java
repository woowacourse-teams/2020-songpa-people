package com.songpapeople.hashtagmap.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InstagramForMarker {
    private Long id;
    private Long hashtagCount;
    private String hashtagName;
    private String placeName;
    private String placeUrl;
    private String kakaoId;
    private Location location;
    private Category category;

    @Builder
    @QueryProjection
    public InstagramForMarker(Long id, Long hashtagCount, String hashtagName, String placeName,
                              String placeUrl, String kakaoId, Location location, Category category) {
        this.id = id;
        this.hashtagCount = hashtagCount;
        this.hashtagName = hashtagName;
        this.placeName = placeName;
        this.placeUrl = placeUrl;
        this.kakaoId = kakaoId;
        this.location = location;
        this.category = category;
    }

    public Instagram toInstagram() {
        return Instagram.builder()
                .id(this.id)
                .hashtagCount(this.hashtagCount)
                .hashtagName(this.hashtagName)
                .place(Place.builder()
                        .placeName(this.placeName)
                        .placeUrl(this.placeUrl)
                        .kakaoId(this.kakaoId)
                        .location(this.location)
                        .category(this.category)
                        .build())
                .build();
    }
}
