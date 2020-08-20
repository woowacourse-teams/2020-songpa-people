package com.songpapeople.hashtagmap.config.schedule;

import com.songpapeople.hashtagmap.kakao.schedule.model.PeriodHistory;
import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.kakao.schedule.repository.PeriodHistoryRepository;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KakaoScheduleAutoConfiguration {
    private static final String KAKAO = "KAKAO";

    private final KakaoScheduler kakaoScheduler;
    private final ScheduleRepository scheduleRepository;
    private final PeriodHistoryRepository periodHistoryRepository;

    @PostConstruct
    public void configureKakaoSchedule() {
        log.info("AutoConfigure KakaoScheduler");
        Optional<Schedule> kakaoSchedule = scheduleRepository.findByName(KAKAO);
        if (kakaoSchedule.isPresent() && kakaoSchedule.get().isActive()) {
            Optional<PeriodHistory> findPeriodHistory = periodHistoryRepository.findTopByOrderByChangedDateDesc();
            findPeriodHistory.ifPresent(periodHistory -> kakaoScheduler.changePeriod(periodHistory.getExpression()));
            kakaoScheduler.start();
        }
    }
}
