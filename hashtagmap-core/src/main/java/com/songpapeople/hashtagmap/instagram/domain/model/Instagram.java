package com.songpapeople.hashtagmap.instagram.domain.model;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "INSTAGRAM_ID"))
@Entity
public class Instagram extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    private Place place;

    private String hashtagName;
    private Long hashtagCount;

    @Builder
    public Instagram(Long id, Place place, String hashtagName, Long hashtagCount) {
        this.id = id;
        this.place = place;
        this.hashtagName = hashtagName;
        this.hashtagCount = hashtagCount;
    }
}
