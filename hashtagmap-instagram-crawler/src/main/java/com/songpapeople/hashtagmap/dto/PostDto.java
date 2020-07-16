package com.songpapeople.hashtagmap.dto;

import lombok.Getter;

@Getter
public class PostDto {
    public static final String POST_URL_FORMAT = "https://www.instagram.com/p/%s";

    private final String postUrl;
    private final String imageUrl;

    public PostDto(String postUrl, String imageUrl) {
        this.postUrl = String.format(POST_URL_FORMAT, postUrl);
        this.imageUrl = imageUrl;
    }
}
