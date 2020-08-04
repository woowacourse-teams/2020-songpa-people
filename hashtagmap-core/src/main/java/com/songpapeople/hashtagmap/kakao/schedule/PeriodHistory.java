package com.songpapeople.hashtagmap.kakao.schedule;

import com.songpapeople.hashtagmap.config.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PeriodHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String expression;
    private String memeber;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changedDate;

    public PeriodHistory(String expression) {
        this.expression = expression;
        this.memeber = "";
    }
}
