package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.scheduler.domain.CronPeriod;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KakaoServiceTest {
    KakaoService kakaoService;

    @BeforeEach
    public void setUp() {
        KakaoScheduler kakaoScheduler = new KakaoScheduler(new Thread(), new CronPeriod("0 0 * * * ?"));
        kakaoScheduler.start();
        kakaoService = new KakaoService(kakaoScheduler);
    }

    @DisplayName("카카오 스케줄러 주기 변경")
    @Test
    public void changeSchedulePeriod() throws Exception {
        String expected = "0 0/5 * * * ?";
        String actual = kakaoService.changeSchedulePeriod(expected);
        assertThat(actual).isEqualTo(expected);
    }
}