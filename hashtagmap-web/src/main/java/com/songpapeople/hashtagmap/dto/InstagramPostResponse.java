package com.songpapeople.hashtagmap.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class InstagramPostResponse {
    private List<InstagramPost> instagramPosts;

    private InstagramPostResponse(List<InstagramPost> instagramPosts) {
        this.instagramPosts = instagramPosts;
    }

    public static InstagramPostResponse of(){
        return new InstagramPostResponse(new ArrayList<>());
    }
}
