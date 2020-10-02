package com.songpapeople.hashtagmap.instagram.domain.model;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "INSTAGRAM_POST_ID"))
@Entity
public class InstagramPost extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSTAGRAM_ID")
    private Instagram instagram;

    private String postUrl;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @Builder
    public InstagramPost(Instagram instagram, String postUrl, String imageUrl) {
        this.instagram = instagram;
        this.postUrl = postUrl;
        this.imageUrl = imageUrl;
    }
}
