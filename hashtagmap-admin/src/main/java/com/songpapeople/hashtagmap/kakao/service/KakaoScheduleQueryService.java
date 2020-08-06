package com.songpapeople.hashtagmap.kakao.service;

import com.songpapeople.hashtagmap.exception.AdminErrorCode;
import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoScheduleQueryService {
    private final ScheduleRepository scheduleRepository;

    public boolean getKakaoScheduleActiveStatus(String name) {
        Schedule schedule = scheduleRepository.findByName(name)
                .orElseThrow(() -> new AdminException(
                        AdminErrorCode.NOT_FOUND_SCHEDULER,
                        String.format("%s : 스케쥴러가 존재하지 않습니다.", name)
                ));
        return schedule.isActive();
    }
}
