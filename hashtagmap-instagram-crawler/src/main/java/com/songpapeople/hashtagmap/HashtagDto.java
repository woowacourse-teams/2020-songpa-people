package com.songpapeople.hashtagmap;

public class HashtagDto {
    private final String placeName;
    private final Long hashtagCount;

    private HashtagDto(String placeName, Long hashtagCount) {
        this.placeName = placeName;
        this.hashtagCount = hashtagCount;
    }

    public static HashtagDto of(String placeName, String hashtagCount) {
        return new HashtagDto(placeName, Long.valueOf(hashtagCount));
    }

    @Override
    public String toString() {
        return "HashtagDto{" +
            "placeName='" + placeName + '\'' +
            ", hashtagCount=" + hashtagCount +
            '}';
    }
}
