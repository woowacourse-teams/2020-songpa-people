package com.songpapeople.hashtagmap.instagram.domain.model;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "INSTAGRAM_POST_ID"))
@Entity
public class InstagramPost extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "INSTAGRAM_ID")
    private Instagram instagram;
    private String imageUrl;
    private String postUrl;

    @Builder
    public InstagramPost(Long id, Instagram instagram, String imageUrl, String postUrl) {
        this.id = id;
        this.instagram = instagram;
        this.imageUrl = imageUrl;
        this.postUrl = postUrl;
    }

}
