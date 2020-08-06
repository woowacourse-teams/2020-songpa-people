package com.songpapeople.hashtagmap.kakao.service;

import com.songpapeople.hashtagmap.config.vo.Flag;
import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import com.songpapeople.hashtagmap.scheduler.exception.KakaoScheduleException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class KakaoScheduleCommandServiceTest {
    private static final String KAKAO = "KAKAO";

    @Autowired
    private KakaoScheduleCommandService kakaoScheduleCommandService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private KakaoScheduler kakaoScheduler;

    @AfterEach
    void tearDown() {
        scheduleRepository.deleteAll();
        kakaoScheduler.stop();
    }

    @DisplayName("스케쥴러 멈추기 성공")
    @Test
    void stopSchedule() {
        //given
        scheduleRepository.save(new Schedule(KAKAO, "비밥", Flag.Y));

        //when
        kakaoScheduleCommandService.toggleSchedule(KAKAO);

        //then
        Schedule schedule = scheduleRepository.findByName(KAKAO).get();
        assertThat(schedule.isActive()).isFalse();
    }

    @DisplayName("멈출 스케쥴러가 존재하지 않을경우 Exception 발생")
    @Test
    void stopScheduleFail() {
        //given
        scheduleRepository.save(new Schedule(KAKAO, "비밥", Flag.N));

        //then
        assertThatThrownBy(() -> kakaoScheduleCommandService.toggleSchedule("LINE"))
                .isInstanceOf(AdminException.class)
                .hasMessage("스케쥴러(LINE)가 존재하지 않습니다.");
    }

    @DisplayName("스케쥴러 시작하기 성공")
    @Test
    void startSchedule() {
        //given
        scheduleRepository.save(new Schedule(KAKAO, "비밥", Flag.N));

        //when
        kakaoScheduleCommandService.toggleSchedule(KAKAO);

        //then
        Schedule schedule = scheduleRepository.findByName(KAKAO).get();
        assertThat(schedule.isActive()).isTrue();
    }

    @DisplayName("스케쥴러 이미 시작한 스케쥴러를 시작하려하는 경우 Exception 발생")
    @Test
    void startAlreadyRunningSchedule() {
        //given
        scheduleRepository.save(new Schedule(KAKAO, "비밥", Flag.Y));
        kakaoScheduler.start();

        //then
        assertThatThrownBy(() -> kakaoScheduleCommandService.toggleSchedule(KAKAO))
                .isInstanceOf(KakaoScheduleException.class)
                .hasMessage("스케쥴러가 이미 실행중입니다.");
    }
}
