package com.songpapeople.hashtagmap.kakaoapi.service;

import com.songpapeople.hashtagmap.kakaoapi.domain.caller.KakaoRestTemplateApiCaller;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.RectDivider;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// TODO: 2020/07/20 서비스 로직이 너무 크니까 리팩토링하기

@Service
public class KakaoService {
    private static final BigDecimal DEFAULT_OFFSET = BigDecimal.valueOf(0.02);
    private static final BigDecimal HALF = BigDecimal.valueOf(2);
    private static final int FIRST_INDEX = 0;
    private static final int FIRST_PAGE = 1;
    private static final int SECOND_PAGE = 2;

    private final KakaoRestTemplateApiCaller kakaoRestTemplateApiCaller;

    public KakaoService(KakaoRestTemplateApiCaller kakaoRestTemplateApiCaller) {
        this.kakaoRestTemplateApiCaller = kakaoRestTemplateApiCaller;
    }

    public List<KakaoPlaceDto> findPlaces(String category, Rect initialRect) {
        List<Rect> dividedRects = RectDivider.divide(initialRect, DEFAULT_OFFSET);
        List<KakaoPlaceDto> result = dividedRects.stream()
                .map(dividedRect -> findPlacesInDividedRect(category, dividedRect, DEFAULT_OFFSET))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return result;
    }

    private List<KakaoPlaceDto> findPlacesInDividedRect(String category, Rect rect, BigDecimal offset) {
        KakaoPlaceDto firstPage = kakaoRestTemplateApiCaller.findPlaceByCategory(category, rect, FIRST_PAGE);

        if (kakaoRestTemplateApiCaller.isLessOrEqualTotalCount(firstPage)) {
            int pageableCount = firstPage.getMeta().getPageableCount();
            List<KakaoPlaceDto> pages = IntStream.rangeClosed(SECOND_PAGE, pageableCount)
                    .mapToObj(index -> kakaoRestTemplateApiCaller.findPlaceByCategory(category, rect, index))
                    .collect(Collectors.toList());
            pages.add(FIRST_INDEX, firstPage);
            return pages;
        }

        return findPlacesInRearrangedRect(category, rect, offset);
    }

    private List<KakaoPlaceDto> findPlacesInRearrangedRect(String category, Rect rect, BigDecimal offset) {
        List<KakaoPlaceDto> pages = new ArrayList<>();
        List<Rect> dividedRects = RectDivider.divide(rect, offset.divide(HALF));
        for (Rect dividedRect : dividedRects) {
            pages.addAll(findPlacesInDividedRect(category, dividedRect, offset.divide(HALF)));
        }
        return pages;
    }
}
