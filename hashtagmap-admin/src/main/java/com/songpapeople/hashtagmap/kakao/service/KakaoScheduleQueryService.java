package com.songpapeople.hashtagmap.kakao.service;

import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.AdminExceptionStatus;
import com.songpapeople.hashtagmap.kakao.schedule.model.PeriodHistory;
import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.kakao.schedule.repository.PeriodHistoryRepository;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import com.songpapeople.hashtagmap.kakao.service.dto.PeriodHistoryDto;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KakaoScheduleQueryService {
    private final ScheduleRepository scheduleRepository;
    private final PeriodHistoryRepository historyRepository;
    private final KakaoScheduler kakaoScheduler;

    public boolean getKakaoScheduleActiveStatus(String name) {
        Schedule schedule = scheduleRepository.findByName(name)
                .orElseThrow(() -> new AdminException(
                        AdminExceptionStatus.NOT_FOUND_SCHEDULER,
                        String.format("%s : 스케쥴러가 존재하지 않습니다.", name)
                ));
        kakaoScheduler.syncSchedule(schedule);
        return schedule.isActive();
    }

    public List<PeriodHistoryDto> showPeriodHistory() {
        List<PeriodHistory> periodHistories = historyRepository.findAll();
        return PeriodHistoryDto.listOf(periodHistories);
    }
}
