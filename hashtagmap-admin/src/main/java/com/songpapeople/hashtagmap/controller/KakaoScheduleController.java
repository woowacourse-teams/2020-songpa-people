package com.songpapeople.hashtagmap.controller;

import com.songpapeople.hashtagmap.kakao.schedule.PeriodHistoryDto;
import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.service.KakaoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kakao/scheduler")
public class KakaoScheduleController {
    private final KakaoService kakaoService;

    public KakaoScheduleController(KakaoService kakaoService) {
        this.kakaoService = kakaoService;
    }

    @PutMapping("/period")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<Void> changeSchedulePeriod(@RequestBody String expression) {
        CustomResponse<Void> response = kakaoService.changeSchedulePeriod(expression);
        return response;
    }

    @GetMapping("/period")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<List<PeriodHistoryDto>> showPeriodHistory() {
        CustomResponse<List<PeriodHistoryDto>> response = kakaoService.showPeriodHistory();
        return response;
    }
}
