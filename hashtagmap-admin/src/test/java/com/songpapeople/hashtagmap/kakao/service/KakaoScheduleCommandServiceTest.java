package com.songpapeople.hashtagmap.kakao.service;

import com.songpapeople.hashtagmap.config.vo.Flag;
import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
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

    @DisplayName("스케쥴러 멈추기 성공")
    @Test
    void stopSchedule() {
        //given
        kakaoScheduler.start();

        //when
        kakaoScheduleCommandService.stopSchedule();

        //then
        assertThat(kakaoScheduler.isActive()).isFalse();
        assertThat(kakaoScheduler.isNotActive()).isTrue();
    }

    @DisplayName("스케쥴러 시작하기 성공")
    @Test
    void startSchedule() {
        //when
        kakaoScheduleCommandService.startSchedule();

        //then
        assertThat(kakaoScheduler.isActive()).isTrue();
        assertThat(kakaoScheduler.isNotActive()).isFalse();
        kakaoScheduler.stop();
    }

    @DisplayName("스케쥴러 자동 실행 상태 변환")
    @ParameterizedTest
    @CsvSource(value = {"Y,false", "N,true"})
    void toggleScheduleAutoRunnable(Flag beforeFlag, boolean expect) {
        //given
        scheduleRepository.save(new Schedule(KAKAO, "", beforeFlag));

        //when
        kakaoScheduleCommandService.toggleScheduleAutoRunnable(KAKAO);

        //then
        Schedule schedule = scheduleRepository.findByName(KAKAO).orElseThrow(RuntimeException::new);
        assertThat(schedule.isActive()).isEqualTo(expect);
    }

    @Test
    void toggleScheduleAutoRunnableNotFound() {
        assertThatThrownBy(() -> kakaoScheduleCommandService.toggleScheduleAutoRunnable(KAKAO))
                .isInstanceOf(AdminException.class)
                .hasMessage("스케쥴러(%s)가 존재하지 않습니다.", KAKAO);
    }

    @DisplayName("카카오 스케줄러 주기 변경")
    @Test
    public void changeSchedulePeriod() throws Exception {
        String expression = "0 0/5 * * * ?";
        assertThatCode(() -> kakaoScheduleCommandService.changeSchedulePeriod(expression))
                .doesNotThrowAnyException();
    }

    @AfterEach
    void tearDown() {
        scheduleRepository.deleteAll();
    }
}
