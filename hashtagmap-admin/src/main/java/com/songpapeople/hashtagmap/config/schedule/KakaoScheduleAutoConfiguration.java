package com.songpapeople.hashtagmap.config.schedule;

import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class KakaoScheduleAutoConfiguration {
    private static final String KAKAO = "KAKAO";

    private final KakaoScheduler kakaoScheduler;
    private final ScheduleRepository scheduleRepository;

    @PostConstruct
    public void configureKakaoSchedule() {
        Optional<Schedule> kakao = scheduleRepository.findByName(KAKAO);
        if (kakao.isPresent() && kakao.get().isActive()) {
            kakaoScheduler.start();
            return;
        }
        kakaoScheduler.stop();
    }
}
