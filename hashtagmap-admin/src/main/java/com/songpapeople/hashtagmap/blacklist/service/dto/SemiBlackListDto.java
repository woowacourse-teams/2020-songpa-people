package com.songpapeople.hashtagmap.blacklist.service.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SemiBlackListDto {
    private String kakaoId;
    private String placeName;
    private String hashtagName;
    private String roadAddressName;
    private Long hashtagCount;

    private SemiBlackListDto(String kakaoId, String placeName, String hashtagName, String roadAddressName, Long hashtagCount) {
        this.kakaoId = kakaoId;
        this.placeName = placeName;
        this.hashtagName = hashtagName;
        this.roadAddressName = roadAddressName;
        this.hashtagCount = hashtagCount;
    }

    public static SemiBlackListDto of(Instagram instagram) {
        return new SemiBlackListDto(
                instagram.getKakaoId(),
                instagram.getPlaceName(),
                instagram.getHashtagName(),
                instagram.getRoadAddressName(),
                instagram.getHashtagCount()
        );
    }
}
