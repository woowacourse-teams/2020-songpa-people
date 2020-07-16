package com.songpapeople.hashtagmap.kakaoapi.service;

import com.songpapeople.hashtagmap.kakaoapi.domain.KakaoPlaceCaller;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.RectDivider;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class KakaoService {
    private static final BigDecimal OFFSET = BigDecimal.valueOf(0.02);

    private final KakaoPlaceCaller kakaoPlaceCaller;

    public KakaoService(KakaoPlaceCaller kakaoPlaceCaller) {
        this.kakaoPlaceCaller = kakaoPlaceCaller;
    }

    // todo 제한 개수 초과했을 때 재귀로 offset 줄이기
    public List<KakaoPlaceDto> findPlaces(String category, Rect rect) {
        List<Rect> dividedRects = RectDivider.divide(rect, OFFSET);
        List<KakaoPlaceDto> result = dividedRects.stream()
                .map(smallRect -> findTotalPlacesBySmallRect(category, smallRect))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return result;
    }

    private List<KakaoPlaceDto> findTotalPlacesBySmallRect(String category, Rect rect) {
        KakaoPlaceDto firstPage = kakaoPlaceCaller.findPlaces(category, rect);
        int pagableCount = firstPage.getMeta().getPageableCount();

        List<KakaoPlaceDto> pages = IntStream.rangeClosed(2, pagableCount)
                .mapToObj(index -> kakaoPlaceCaller.findPlaces(category, rect, index))
                .collect(Collectors.toList());
        pages.add(0, firstPage);
        return pages;
    }
}
