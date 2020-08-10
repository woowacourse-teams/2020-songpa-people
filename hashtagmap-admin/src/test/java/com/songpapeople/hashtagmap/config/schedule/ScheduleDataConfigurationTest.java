package com.songpapeople.hashtagmap.config.schedule;

import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ScheduleDataConfigurationTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @DisplayName("profile=data 환경에서 schedule 정보 한개 주입")
    @Test
    void run() throws Exception {
        ScheduleDataConfiguration configuration = new ScheduleDataConfiguration(scheduleRepository);
        configuration.run(new DefaultApplicationArguments(""));

        List<Schedule> schedules = scheduleRepository.findAll();
        assertThat(schedules).hasSize(1);
        assertThat(schedules.get(0).isActive()).isEqualTo(false);
    }

    @AfterEach
    void tearDown() {
        scheduleRepository.deleteAll();
    }
}