package com.songpapeople.hashtagmap.blacklist.domain.model;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "BLACK_LIST_ID"))
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "kakaoId"))
@Entity
public class BlackList extends BaseEntity {
    private String kakaoId;
    private String replaceName;

    public BlackList(String kakaoId, String replaceName) {
        this.kakaoId = kakaoId;
        this.replaceName = replaceName;
    }
}
