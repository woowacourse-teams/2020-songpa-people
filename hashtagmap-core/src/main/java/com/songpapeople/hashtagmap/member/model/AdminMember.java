package com.songpapeople.hashtagmap.member.model;


import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AttributeOverride(name = "id", column = @Column(name = "ADMIN_MEMBER_ID"))
@Entity
public class AdminMember extends BaseEntity {
    private String nickName;
    private String password;

    public AdminMember(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
    }

    public boolean isNotMatchPassword(String password) {
        return !Objects.equals(this.password, password);
    }
}
