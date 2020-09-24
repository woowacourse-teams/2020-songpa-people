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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "kakaoId", name = "UK_KAKAO_BLACK_LIST"))
@Entity
public class BlackList extends BaseEntity {
    private String kakaoId;
    private String replaceName;
    private Boolean isSkipPlace;

    public BlackList(String kakaoId, String replaceName) {
        this(kakaoId, replaceName, false);
    }

    public BlackList(String kakaoId, String replaceName, Boolean isSkipPlace) {
        this.kakaoId = kakaoId;
        this.replaceName = replaceName;
        this.isSkipPlace = isSkipPlace;
    }

    public void updateBlackList(BlackList blackList) {
        this.replaceName = blackList.replaceName;
        this.isSkipPlace = blackList.isSkipPlace;
    }
}
