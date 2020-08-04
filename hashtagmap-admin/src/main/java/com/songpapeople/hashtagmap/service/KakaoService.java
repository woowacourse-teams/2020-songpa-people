package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import org.springframework.stereotype.Service;

@Service
public class KakaoService {
    private final KakaoScheduler kakaoScheduler;

    public KakaoService(KakaoScheduler kakaoScheduler) {
        this.kakaoScheduler = kakaoScheduler;
    }

    public String changeSchedulePeriod(String expression) {
        return kakaoScheduler.changePeriod(expression);
    }
}
