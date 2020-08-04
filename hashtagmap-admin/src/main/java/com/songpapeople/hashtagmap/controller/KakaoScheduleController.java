package com.songpapeople.hashtagmap.controller;

import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.service.KakaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kakao-scheduler")
public class KakaoScheduleController {
    private final KakaoService kakaoService;

    public KakaoScheduleController(KakaoService kakaoService) {
        this.kakaoService = kakaoService;
    }

    @PostMapping("/change-period")
    public ResponseEntity<CustomResponse> changeSchedulePeriod(@RequestBody String expression) {
        System.out.println("@@@@@ Controller Expression: " + expression);
        CustomResponse response = kakaoService.changeSchedulePeriod(expression);

        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/period-history")
    public ResponseEntity<CustomResponse> showPeriodHistory() {
        CustomResponse response = kakaoService.showPeriodHistory();

        return ResponseEntity.ok()
                .body(response);
    }
}
