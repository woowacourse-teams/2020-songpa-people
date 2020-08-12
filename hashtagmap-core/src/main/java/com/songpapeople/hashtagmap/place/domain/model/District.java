package com.songpapeople.hashtagmap.place.domain.model;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "DISTRICT_ID"))
@Table(uniqueConstraints = @UniqueConstraint(name = "UK_DISTRICT_NAME", columnNames = "DISTRICT_NAME"))
@Entity
public class District extends BaseEntity {
    @Column(name = "DISTRICT_NAME")
    private String districtName;

    public District(final String districtName) {
        this.districtName = districtName.trim();
    }

    public void update(final String districtName) {
        if (!StringUtils.isEmpty(districtName)) {
            this.districtName = districtName;
        }
    }
}
