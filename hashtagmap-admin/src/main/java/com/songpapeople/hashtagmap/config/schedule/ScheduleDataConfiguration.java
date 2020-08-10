package com.songpapeople.hashtagmap.config.schedule;

import com.songpapeople.hashtagmap.config.vo.Flag;
import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Generated
@Profile("data")
@Configuration
@RequiredArgsConstructor
public class ScheduleDataConfiguration implements ApplicationRunner {

    private final ScheduleRepository scheduleRepository;

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        Schedule schedule = scheduleRepository.findByName("KAKAO")
                .orElseGet(() -> new Schedule("KAKAO", "auto", Flag.N));
        scheduleRepository.save(schedule);
    }
}
