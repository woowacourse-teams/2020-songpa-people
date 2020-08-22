package com.songpapeople.hashtagmap.kakao.service;

import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.AdminExceptionStatus;
import com.songpapeople.hashtagmap.kakao.schedule.model.PeriodHistory;
import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.kakao.schedule.repository.PeriodHistoryRepository;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KakaoScheduleCommandService {
    private final PeriodHistoryRepository historyRepository;
    private final ScheduleRepository scheduleRepository;
    private final KakaoScheduler kakaoScheduler;

    @Transactional
    public void changeSchedulePeriod(String expression) {
        historyRepository.save(new PeriodHistory(expression));
        kakaoScheduler.changePeriod(expression);
    }

    @Transactional
    public void toggleScheduleAutoRunnable(final String name) {
        Schedule schedule = scheduleRepository.findByName(name)
                .orElseThrow(() -> new AdminException(
                        AdminExceptionStatus.NOT_FOUND_SCHEDULER,
                        String.format("스케쥴러(%s)가 존재하지 않습니다.", name)
                ));
        schedule.toggle();
    }

    public void startSchedule() {
        kakaoScheduler.start();
    }

    public void stopSchedule() {
        kakaoScheduler.stop();
    }

}
