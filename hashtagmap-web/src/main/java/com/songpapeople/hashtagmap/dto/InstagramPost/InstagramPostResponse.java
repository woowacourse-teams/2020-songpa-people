package com.songpapeople.hashtagmap.dto.InstagramPost;

import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class InstagramPostResponse {
    private String imageUrl;
    private String postUrl;

    @Builder
    public InstagramPostResponse(String imageUrl, String postUrl) {
        this.imageUrl = imageUrl;
        this.postUrl = postUrl;
    }

    public static InstagramPostResponse of(InstagramPost instagramPosts) {
        return InstagramPostResponse.builder()
                .imageUrl(instagramPosts.getImageUrl())
                .postUrl(instagramPosts.getPostUrl())
                .build();
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final InstagramPostResponse that = (InstagramPostResponse) o;
        return Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(postUrl, that.postUrl);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(imageUrl, postUrl);
    }
}
