package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.kakao.schedule.repository.PeriodHistoryRepository;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import com.songpapeople.hashtagmap.kakao.service.KakaoScheduleCommandService;
import com.songpapeople.hashtagmap.scheduler.domain.CronPeriod;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KakaoServiceTest {
    private KakaoScheduleCommandService kakaoService;

    @Mock
    private PeriodHistoryRepository periodHistoryRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @BeforeEach
    public void setUp() {
        KakaoScheduler kakaoScheduler = new KakaoScheduler(new Thread(), new CronPeriod("0 0 * * * ?"));
        kakaoService = new KakaoScheduleCommandService(periodHistoryRepository, scheduleRepository, kakaoScheduler);
    }

    @DisplayName("카카오 스케줄러 주기 변경")
    @Test
    public void changeSchedulePeriod() throws Exception {
        when(periodHistoryRepository.save(any())).thenReturn(null);

        String expression = "0 0/5 * * * ?";
        assertThatCode(() -> kakaoService.changeSchedulePeriod(expression))
                .doesNotThrowAnyException();
    }
}