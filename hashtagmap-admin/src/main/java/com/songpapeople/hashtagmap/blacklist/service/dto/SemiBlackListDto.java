package com.songpapeople.hashtagmap.blacklist.service.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SemiBlackListDto {
    private Long placeId;
    private String placeName;
    private String hashtagName;
    private String roadAddressName;
    private Long hashtagCount;

    private SemiBlackListDto(Long placeId, String placeName, String hashtagName, String roadAddressName, Long hashtagCount) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.hashtagName = hashtagName;
        this.roadAddressName = roadAddressName;
        this.hashtagCount = hashtagCount;
    }

    public static SemiBlackListDto of(Instagram instagram) {
        return new SemiBlackListDto(
                instagram.getPlaceId(),
                instagram.getPlaceName(),
                instagram.getHashtagName(),
                instagram.getRoadAddressName(),
                instagram.getHashtagCount()
        );
    }
}
