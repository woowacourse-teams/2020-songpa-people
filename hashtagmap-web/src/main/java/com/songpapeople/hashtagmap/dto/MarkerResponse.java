package com.songpapeople.hashtagmap.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import lombok.Builder;
import lombok.Getter;

import java.util.Random;

@Getter
public class MarkerResponse {
    private final String placeName;
    private final String placeUrl;
    private final String kakaoId;
    private final Long instagramId;
    private final Long hashtagCount;
    private final String hashtagName;
    private final Integer tagLevel;
    private final String latitude;
    private final String longitude;
    private final String category;

    @Builder
    private MarkerResponse(String placeName, String placeUrl, String kakaoId, Long instagramId, Long hashtagCount,
                           String hashtagName, Integer tagLevel, String latitude, String longitude, String category) {
        this.placeName = placeName;
        this.placeUrl = placeUrl;
        this.kakaoId = kakaoId;
        this.instagramId = instagramId;
        this.hashtagCount = hashtagCount;
        this.hashtagName = hashtagName;
        this.tagLevel = tagLevel;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
    }

    // TODO: 2020-08-05 Tag Level 정해지면 수정, 우선 랜덤으로..
    public static MarkerResponse from(Instagram instagram) {
        Place place = instagram.getPlace();
        return MarkerResponse.builder()
                .placeName(place.getPlaceName())
                .placeUrl(place.getPlaceUrl())
                .kakaoId(place.getKakaoId())
                .instagramId(instagram.getId())
                .hashtagCount(instagram.getHashtagCount())
                .hashtagName(instagram.getHashtagName())
                .tagLevel(new Random().nextInt(5) + 1)
                .latitude(place.getLocation().getPoint().getLatitude())
                .longitude(place.getLocation().getPoint().getLongitude())
                .category(place.getCategory().getCategoryGroupName())
                .build();
    }
}
