package com.songpapeople.hashtagmap.instagram.domain.model.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InstagramForBlacklist {
    private String hashtagName;
    private Long hashtagCount;
    private String kakaoId;
    private String placeName;
    private String roadAddressName;

    @Builder
    public InstagramForBlacklist(String hashtagName, Long hashtagCount, String kakaoId, String placeName, String roadAddressName) {
        this.hashtagName = hashtagName;
        this.hashtagCount = hashtagCount;
        this.kakaoId = kakaoId;
        this.placeName = placeName;
        this.roadAddressName = roadAddressName;
    }

    public Instagram toInstagram() {
        return Instagram.builder()
                .place(Place.builder()
                        .placeName(this.placeName)
                        .kakaoId(this.kakaoId)
                        .location(new Location(null, this.roadAddressName))
                        .build())
                .hashtagName(this.hashtagName)
                .hashtagCount(this.hashtagCount)
                .build();
    }
}
