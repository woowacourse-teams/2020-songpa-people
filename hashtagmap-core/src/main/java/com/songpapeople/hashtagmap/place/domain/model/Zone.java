package com.songpapeople.hashtagmap.place.domain.model;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
