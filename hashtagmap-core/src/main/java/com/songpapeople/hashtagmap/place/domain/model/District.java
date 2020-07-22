package com.songpapeople.hashtagmap.place.domain.model;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "DISTRICT_ID"))
@Entity
public class District extends BaseEntity {
    private String district;
    private boolean activate;
}
