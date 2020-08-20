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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "INSTAGRAM_ID"))
@Entity
public class Instagram extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
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

    public String getPlaceName() {
        return place.getPlaceName();
    }

    public String getRoadAddressName() {
        return place.getRoadAddressName();
    }

    public Long getPlaceId() {
        return place.getId();
    }

    public void setHashtagName(String hashtagName) {
        this.hashtagName = hashtagName;
    }

    public void setHashtagCount(Long hashtagCount) {
        this.hashtagCount = hashtagCount;
    }
}
