package com.songpapeople.hashtagmap.blacklist.service.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SubBlackListDto {
    private String placeName;
    private String roadAddressName;
    private Long HashtagCount;

    private SubBlackListDto(String placeName, String roadAddressName, Long hashtagCount) {
        this.placeName = placeName;
        this.roadAddressName = roadAddressName;
        HashtagCount = hashtagCount;
    }

    public static SubBlackListDto of(Instagram instagram) {
        return new SubBlackListDto(
                instagram.getPlaceName(),
                instagram.getRoadAddressName(),
                instagram.getHashtagCount()
        );
    }
}
