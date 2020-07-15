package com.songpapeople.hashtagmap.dto;

public class PostDto {
    public static final String POST_URL_FORMAT = "https://www.instagram.com/p/%s";

    private String postUrl;
    private String imageUrl;

    public PostDto(String postUrl, String imageUrl) {
        this.postUrl = String.format(POST_URL_FORMAT, postUrl);
        this.imageUrl = imageUrl;
    }
}
