package com.songpapeople.hashtagmap.config.schedule;

import com.songpapeople.hashtagmap.config.vo.Flag;
import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class KakaoScheduleAutoConfigurationTest {
    private static final String KAKAO = "KAKAO";

    @Autowired
    private KakaoScheduleAutoConfiguration kakaoScheduleAutoConfiguration;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private KakaoScheduler kakaoScheduler;

    @DisplayName("Kakao 스케쥴러가 실행되지 않도록 설정")
    @Test
    void toStartConfigureKakaoSchedule() {
        //given
        scheduleRepository.save(new Schedule(KAKAO, "비밥", Flag.N));

        //when
        kakaoScheduleAutoConfiguration.configureKakaoSchedule();

        //then
        boolean result = kakaoScheduler.stop();
        assertThat(result).isFalse();
    }

    @DisplayName("Kakao 스케쥴러가 실행되도록 설정")
    @Test
    void toStopConfigureKakaoSchedule() {
        //given
        scheduleRepository.save(new Schedule(KAKAO, "비밥", Flag.Y));

        //when
        kakaoScheduleAutoConfiguration.configureKakaoSchedule();

        //then
        boolean result = kakaoScheduler.stop();
        assertThat(result).isTrue();
    }

    @AfterEach
    void tearDown() {
        scheduleRepository.deleteAll();
        kakaoScheduler.stop();
    }
}