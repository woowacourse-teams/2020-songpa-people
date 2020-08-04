package com.songpapeople.hashtagmap.controller;

import com.songpapeople.hashtagmap.service.KakaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/kakao-scheduler")
public class KakaoScheduleController {
    private final KakaoService kakaoService;

    public KakaoScheduleController(KakaoService kakaoService) {
        this.kakaoService = kakaoService;
    }

    @PostMapping("/change-period")
    public ResponseEntity changeSchedulePeriod(@RequestBody String expression) {
        if (Objects.isNull(expression) || expression.isEmpty()) {
            return ResponseEntity.badRequest()
                    .build();
        }
        kakaoService.changeSchedulePeriod(expression);
        return ResponseEntity.noContent()
                .build();
    }
}
