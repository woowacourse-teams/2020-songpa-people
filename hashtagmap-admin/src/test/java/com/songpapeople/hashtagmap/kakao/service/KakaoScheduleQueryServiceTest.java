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
    private static final String INSTAGRAM = "INSTAGRAM";
    @Autowired
    private KakaoScheduleQueryService kakaoScheduleQueryService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @DisplayName("입력한 스케쥴러의 이름으로 스케쥴러의 활성화 상태 조회하기")
    @Test
    void getKakaoScheduleActiveStatus() {
        //given
        scheduleRepository.save(new Schedule(KAKAO, "비밥", Flag.Y));
        scheduleRepository.save(new Schedule(INSTAGRAM, "비밥", Flag.N));

        //when
        boolean kakaoScheduleActiveStatus = kakaoScheduleQueryService.getKakaoScheduleActiveStatus(KAKAO);

        //then
        assertThat(kakaoScheduleActiveStatus).isTrue();
    }

    @DisplayName("입력한 스케쥴러의 이름에 맞는 스케쥴러가 없는 경우 Exception")
    @Test
    void getKakaoScheduleActiveStatusException() {
        //given
        scheduleRepository.save(new Schedule(INSTAGRAM, "비밥", Flag.N));

        //then
        assertThatThrownBy(() -> kakaoScheduleQueryService.getKakaoScheduleActiveStatus(KAKAO))
                .isInstanceOf(AdminException.class)
                .hasMessage("%s : 스케쥴러가 존재하지 않습니다.", KAKAO);
    }

    @AfterEach
    void tearDown() {
        scheduleRepository.deleteAll();
    }
}