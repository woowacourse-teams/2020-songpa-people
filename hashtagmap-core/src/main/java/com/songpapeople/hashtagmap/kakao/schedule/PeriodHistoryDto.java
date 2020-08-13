package com.songpapeople.hashtagmap.kakao.schedule;

import com.songpapeople.hashtagmap.kakao.schedule.model.PeriodHistory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PeriodHistoryDto {
    private Long id;
    private String expression;
    private String member;
    private Date changedDate;

    @Builder
    public PeriodHistoryDto(Long id, String expression, String member, Date changedDate) {
        this.id = id;
        this.expression = expression;
        this.member = member;
        this.changedDate = changedDate;
    }

    public static PeriodHistoryDto from(PeriodHistory periodHistory) {
        return PeriodHistoryDto.builder()
                .id(periodHistory.getId())
                .expression(periodHistory.getExpression())
                .member(periodHistory.getMember())
                .changedDate(periodHistory.getChangedDate())
                .build();
    }
}
