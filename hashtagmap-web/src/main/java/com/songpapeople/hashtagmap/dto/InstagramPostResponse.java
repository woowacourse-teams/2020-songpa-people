package com.songpapeople.hashtagmap.dto;

import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class InstagramPostResponse {
    private Long id;
    private String imageUrl;
    private String postUrl;

    @Builder
    public InstagramPostResponse(Long id, String imageUrl, String postUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.postUrl = postUrl;
    }

    public static InstagramPostResponse of(InstagramPost instagramPosts) {
        return InstagramPostResponse.builder()
                .id(instagramPosts.getId())
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
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getImageUrl(), that.getImageUrl()) &&
                Objects.equals(getPostUrl(), that.getPostUrl());
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getImageUrl(), getPostUrl());
    }
}
