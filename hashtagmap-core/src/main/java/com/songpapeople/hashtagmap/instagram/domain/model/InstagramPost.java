package com.songpapeople.hashtagmap.instagram.domain.model;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "INSTAGRAM_POST_ID"))
@Entity
public class InstagramPost extends BaseEntity {
    private Long instagramId;
    private String postUrl;
    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @Builder
    public InstagramPost(Long id, Long instagramId, String imageUrl, String postUrl) {
        this.id = id;
        this.instagramId = instagramId;
        this.imageUrl = imageUrl;
        this.postUrl = postUrl;
    }
}
