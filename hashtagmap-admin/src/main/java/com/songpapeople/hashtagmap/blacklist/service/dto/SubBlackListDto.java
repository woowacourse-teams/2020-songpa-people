package com.songpapeople.hashtagmap.blacklist.service.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SubBlackListDto {
    private Long placeId;
    private String placeName;
    private String roadAddressName;
    private Long HashtagCount;

    private SubBlackListDto(Long placeId, String placeName, String roadAddressName, Long hashtagCount) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.roadAddressName = roadAddressName;
        HashtagCount = hashtagCount;
    }

    public static SubBlackListDto of(Instagram instagram) {
        return new SubBlackListDto(
                instagram.getPlaceId(),
                instagram.getPlaceName(),
                instagram.getRoadAddressName(),
                instagram.getHashtagCount()
        );
    }
}
