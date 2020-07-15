package com.songpapeople.hashtagmap;

import java.util.List;

import lombok.ToString;

@ToString
public class HashtagDto {
    private final String placeName;
    private final Long hashtagCount;
    private final List<String> imageUrls;

    private HashtagDto(String placeName, Long hashtagCount, List<String> imageUrls) {
        this.placeName = placeName;
        this.hashtagCount = hashtagCount;
        this.imageUrls = imageUrls;
    }

    public static HashtagDto of(String placeName, String hashtagCount, List<String> imageUrls) {
        return new HashtagDto(placeName, Long.valueOf(hashtagCount), imageUrls);
    }
}
