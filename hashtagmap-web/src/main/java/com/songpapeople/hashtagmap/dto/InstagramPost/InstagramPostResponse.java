package com.songpapeople.hashtagmap.dto.InstagramPost;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class InstagramPostResponse {
    private String imageUrl;
    private String postUrl;

    public InstagramPostResponse(String imageUrl, String postUrl) {
        this.imageUrl = imageUrl;
        this.postUrl = postUrl;
    }
}
