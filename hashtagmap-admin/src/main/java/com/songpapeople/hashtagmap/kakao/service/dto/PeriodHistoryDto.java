package com.songpapeople.hashtagmap.kakao.service.dto;

import com.songpapeople.hashtagmap.kakao.schedule.model.PeriodHistory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static List<PeriodHistoryDto> listOf(final List<PeriodHistory> periodHistories) {
        return periodHistories.stream()
                .map(PeriodHistoryDto::of)
                .collect(Collectors.toList());
    }

    private static PeriodHistoryDto of(PeriodHistory periodHistory) {
        return PeriodHistoryDto.builder()
                .id(periodHistory.getId())
                .changedDate(periodHistory.getChangedDate())
                .expression(periodHistory.getExpression())
                .member(periodHistory.getMember())
                .build();
    }
}
