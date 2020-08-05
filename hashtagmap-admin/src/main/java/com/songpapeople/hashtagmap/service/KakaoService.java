package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.kakao.schedule.PeriodHistory;
import com.songpapeople.hashtagmap.kakao.schedule.PeriodHistoryDto;
import com.songpapeople.hashtagmap.kakao.schedule.PeriodHistoryRepository;
import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KakaoService {
    private final KakaoScheduler kakaoScheduler;
    private final PeriodHistoryRepository historyRepository;

    public KakaoService(KakaoScheduler kakaoScheduler, PeriodHistoryRepository historyRepository) {
        this.kakaoScheduler = kakaoScheduler;
        this.historyRepository = historyRepository;
    }

    public CustomResponse<Void> changeSchedulePeriod(String expression) {
        kakaoScheduler.changePeriod(expression);
        historyRepository.save(new PeriodHistory(expression));

        return CustomResponse.empty();
    }

    public CustomResponse<List<PeriodHistoryDto>> showPeriodHistory() {
        List<PeriodHistoryDto> histories = historyRepository.findAll()
                .stream()
                .map(PeriodHistory::toDto)
                .collect(Collectors.toList());

        return CustomResponse.of(histories);
    }
}
