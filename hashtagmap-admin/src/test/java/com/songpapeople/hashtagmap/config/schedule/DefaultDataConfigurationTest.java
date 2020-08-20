package com.songpapeople.hashtagmap.config.schedule;

import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import com.songpapeople.hashtagmap.taglevel.model.TagLevel;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class DefaultDataConfigurationTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private TagLevelRepository tagLevelRepository;

    @DisplayName("profile=data 환경 데이터 주입")
    @TestFactory
    Collection<DynamicTest> configTest() throws Exception {
        DefaultDataConfiguration configuration = new DefaultDataConfiguration(scheduleRepository, tagLevelRepository);
        configuration.run(new DefaultApplicationArguments(""));

        return Arrays.asList(
                DynamicTest.dynamicTest("schedule 정보 조회", () -> {
                    List<Schedule> schedules = scheduleRepository.findAll();
                    assertThat(schedules).hasSize(1);
                    assertThat(schedules.get(0).isActive()).isEqualTo(false);
                }),
                DynamicTest.dynamicTest("tagLevel 정보 조회", () -> {
                    List<TagLevel> tagLevels = tagLevelRepository.findAll();
                    assertThat(tagLevels).hasSize(5);
                })
        );
    }

    @AfterEach
    void tearDown() {
        scheduleRepository.deleteAll();
        tagLevelRepository.deleteAll();
    }
}