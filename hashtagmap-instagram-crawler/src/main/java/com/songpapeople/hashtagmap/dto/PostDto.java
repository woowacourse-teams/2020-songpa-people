package com.songpapeople.hashtagmap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class PostDto {
    public static final String POST_URL_FORMAT = "https://www.instagram.com/p/%s";

    private final String postUrl;
    private final String imageUrl;

}
