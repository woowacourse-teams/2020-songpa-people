package com.songpapeople.hashtagmap.kakao.service;

import com.songpapeople.hashtagmap.config.vo.Flag;
import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class KakaoScheduleQueryServiceTest {

    private static final String KAKAO = "KAKAO";
    @Autowired
    private KakaoScheduleQueryService kakaoScheduleQueryService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @DisplayName("카카오 스케쥴러의 활성화 상태 조회하기")
    @Test
    void getKakaoScheduleActiveStatus() {
        //when
        boolean kakaoScheduleActiveStatus = kakaoScheduleQueryService.getKakaoScheduleActiveStatus();

        //then
        assertThat(kakaoScheduleActiveStatus).isFalse();
    }

    @DisplayName("카카오 스케쥴러의 자동실행 가능 상태를 조회한다.")
    @Test
    void getKakaoScheduleAutoRunnable() {
        //given
        scheduleRepository.save(new Schedule(KAKAO, "", Flag.Y));

        //when
        boolean kakaoAutoRunnable = kakaoScheduleQueryService.getKakaoScheduleAutoRunnable(KAKAO);

        //then
        assertThat(kakaoAutoRunnable).isTrue();
    }

    @DisplayName("KAKAO 스케쥴러를 찾지 못한 경우")
    @Test
    void getKakaoScheduleAutoRunnableNotFound() {
        assertThatThrownBy(() -> kakaoScheduleQueryService.getKakaoScheduleAutoRunnable(KAKAO))
                .isInstanceOf(AdminException.class)
                .hasMessage("%s : 스케쥴러가 존재하지 않습니다.", KAKAO);
    }

    @AfterEach
    void tearDown() {
        scheduleRepository.deleteAll();
    }
}