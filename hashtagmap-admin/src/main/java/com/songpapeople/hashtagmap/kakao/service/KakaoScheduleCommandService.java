package com.songpapeople.hashtagmap.kakao.service;

import com.songpapeople.hashtagmap.exception.AdminErrorCode;
import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KakaoScheduleCommandService {
    private final ScheduleRepository scheduleRepository;
    private final KakaoScheduler kakaoScheduler;

    @Transactional
    public void toggleSchedule(String target) {
        Schedule schedule = findScheduleByTarget(target);
        schedule.toggle();
        if (schedule.isActive()) {
            kakaoScheduler.stop();
            return;
        }
        kakaoScheduler.start();
    }

    private Schedule findScheduleByTarget(final String target) {
        return scheduleRepository.findByTarget(target)
                .orElseThrow(() -> new AdminException(
                        AdminErrorCode.NOT_FOUND_SCHEDULER,
                        String.format("스케쥴러(%s)가 존재하지 않습니다.", target)
                ));
    }
}
