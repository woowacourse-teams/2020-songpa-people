package com.songpapeople.hashtagmap.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import lombok.Getter;

import java.util.Random;

@Getter
public class MarkerResponse {
    private final String placeName;
    private final String kakaoId;
    private final Long hashtagCount;
    private final Integer tagLevel;
    private final String latitude;
    private final String longitude;

    private MarkerResponse(String placeName, String kakaoId, Long hashtagCount,
                           Integer tagLevel, String latitude, String longitude) {
        this.placeName = placeName;
        this.kakaoId = kakaoId;
        this.hashtagCount = hashtagCount;
        this.tagLevel = tagLevel;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static MarkerResponse from(Instagram instagram) {
        Place place = instagram.getPlace();
        return new MarkerResponse(
                place.getPlaceName(),
                place.getKakaoId(),
                instagram.getHashtagCount(),
                new Random().nextInt(5) + 1,    // tagLevel 랜덤으로 넣어두겠슴다.
                place.getLocation().getPoint().getLatitude(),
                place.getLocation().getPoint().getLongitude());
    }
}
