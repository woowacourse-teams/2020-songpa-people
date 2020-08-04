package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.kakao.schedule.PeriodHistory;
import com.songpapeople.hashtagmap.kakao.schedule.PeriodHistoryRepository;
import com.songpapeople.hashtagmap.response.CustomResponse;
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

    public CustomResponse changeSchedulePeriod(String expression) {
        kakaoScheduler.changePeriod(expression);
        historyRepository.save(new PeriodHistory(expression));
        return CustomResponse.of("카카오 스케줄러 주기가 변경되었습니다.");
    }
}
