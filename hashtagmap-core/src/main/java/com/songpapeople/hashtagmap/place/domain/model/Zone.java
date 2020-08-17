package com.songpapeople.hashtagmap.place.domain.model;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "ZONE_ID"))
@Entity
public class Zone extends BaseEntity {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "MIN_LATITUDE")),
            @AttributeOverride(name = "longitude", column = @Column(name = "MAX_LONGITUDE"))
    })
    private Point topLeft;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "MAX_LATITUDE")),
            @AttributeOverride(name = "longitude", column = @Column(name = "MIN_LONGITUDE"))
    })
    private Point bottomRight;

    @ManyToOne
    @JoinColumn(name = "DISTRICT_ID", foreignKey = @ForeignKey(name = "FK_DISTRICT_ZONE"))
    private District district;

    private boolean isActivated;

    @Builder
    public Zone(Point topLeft, Point bottomRight, District district, boolean isActivated) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.district = district;
        this.isActivated = isActivated;
    }

    public void updatePosition(final String topLeftLatitude, final String topLeftLongitude, final String bottomRightLatitude, final String bottomRightLongitude) {
        if (!StringUtils.isEmpty(topLeftLatitude)) {
            this.topLeft.changeLatitude(topLeftLatitude);
        }
        if (!StringUtils.isEmpty(topLeftLongitude)) {
            this.topLeft.changeLongitude(topLeftLongitude);
        }
        if (!StringUtils.isEmpty(bottomRightLatitude)) {
            this.bottomRight.changeLatitude(bottomRightLatitude);
        }
        if (!StringUtils.isEmpty(bottomRightLongitude)) {
            this.bottomRight.changeLongitude(bottomRightLongitude);
        }
    }

    public void changeDistrict(final District district) {
        if (district == null) {
            return;
        }
        this.district = district;
    }

    public String getMinLatitude() {
        return topLeft.getLatitude();
    }

    public String getMaxLatitude() {
        return bottomRight.getLatitude();
    }

    public String getMinLongitude() {
        return bottomRight.getLongitude();
    }

    public String getMaxLongitude() {
        return topLeft.getLongitude();
    }
}
