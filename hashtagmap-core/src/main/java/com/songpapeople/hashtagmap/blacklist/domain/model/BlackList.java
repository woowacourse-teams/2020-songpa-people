package com.songpapeople.hashtagmap.blacklist.domain.model;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "BLACK_LIST_ID"))
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "placeId"))
@Entity
public class BlackList extends BaseEntity {
    private Long placeId;
    private String replaceName;
    @Column(columnDefinition = "bit default 0")
    private Boolean isSkipPlace;

    public BlackList(Long placeId, String replaceName) {
        this.placeId = placeId;
        this.replaceName = replaceName;
        this.isSkipPlace = false;
    }

    public void setSkipPlace(Boolean isSkipPlace) {
        this.isSkipPlace = isSkipPlace;
    }

    public void setReplaceName(String replaceName) {
        this.replaceName = replaceName;
    }
}
