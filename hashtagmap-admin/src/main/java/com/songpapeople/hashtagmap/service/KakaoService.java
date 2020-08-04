package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.kakao.schedule.PeriodHistory;
import com.songpapeople.hashtagmap.kakao.schedule.PeriodHistoryRepository;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import org.springframework.stereotype.Service;

@Service
public class KakaoService {
    private final KakaoScheduler kakaoScheduler;
    private final PeriodHistoryRepository historyRepository;

    public KakaoService(KakaoScheduler kakaoScheduler, PeriodHistoryRepository historyRepository) {
        this.kakaoScheduler = kakaoScheduler;
        this.historyRepository = historyRepository;
    }

    public String changeSchedulePeriod(String expression) {
        kakaoScheduler.changePeriod(expression);
        historyRepository.save(new PeriodHistory(expression));
        return expression;
    }
}
