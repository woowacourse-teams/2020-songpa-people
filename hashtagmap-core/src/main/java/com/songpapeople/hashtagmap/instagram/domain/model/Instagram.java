package com.songpapeople.hashtagmap.instagram.domain.model;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "INSTAGRAM_ID"))
@Entity
public class Instagram extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PLACE_ID")
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
