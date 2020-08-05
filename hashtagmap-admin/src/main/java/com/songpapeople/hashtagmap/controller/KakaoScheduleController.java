package com.songpapeople.hashtagmap.controller;

import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.service.KakaoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kakao")
public class KakaoScheduleController {
    private final KakaoService kakaoService;

    public KakaoScheduleController(KakaoService kakaoService) {
        this.kakaoService = kakaoService;
    }

    @PutMapping("/scheduler/period")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse changeSchedulePeriod(@RequestBody String expression) {
        CustomResponse response = kakaoService.changeSchedulePeriod(expression);
        return response;
    }

    @GetMapping("/scheduler/period")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse showPeriodHistory() {
        CustomResponse response = kakaoService.showPeriodHistory();
        return response;
    }
}
