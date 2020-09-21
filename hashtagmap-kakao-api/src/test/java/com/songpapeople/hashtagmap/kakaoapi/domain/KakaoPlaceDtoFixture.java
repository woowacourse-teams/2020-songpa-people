package com.songpapeople.hashtagmap.kakaoapi.domain;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Document;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Meta;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Latitude;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Longitude;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KakaoPlaceDtoFixture {
    public static final int MAX_PAGEABLE_COUNT = 2;
    public static final int MAX_DOCUMENT_COUNT = 5;

    public static Rect initialRect;
    public static List<Rect> rects;
    public static List<Rect> dividedRects;
    public static Map<Rect, Integer> totalCounts;

    static {
        Latitude latitude = new Latitude(33);
        Longitude longitude = new Longitude(132);
        initialRect = Rect.byOffset(latitude, longitude, 0.04);

        BigDecimal rectOffset = BigDecimal.valueOf(0.02);
        rects = Arrays.asList(Rect.byOffset(latitude, longitude, rectOffset),
                Rect.byOffset(latitude.forward(rectOffset), longitude, rectOffset),
                Rect.byOffset(latitude, longitude.forward(rectOffset), rectOffset),
                Rect.byOffset(latitude.forward(rectOffset), longitude.forward(rectOffset), rectOffset));

        BigDecimal dividedRectOffset = BigDecimal.valueOf(0.01);
        dividedRects = Arrays.asList(Rect.byOffset(latitude, longitude, dividedRectOffset),
                Rect.byOffset(latitude.forward(dividedRectOffset), longitude, dividedRectOffset),
                Rect.byOffset(latitude, longitude.forward(dividedRectOffset), dividedRectOffset),
                Rect.byOffset(latitude.forward(dividedRectOffset), longitude.forward(dividedRectOffset), dividedRectOffset));

        totalCounts = new HashMap<>();
        totalCounts.put(rects.get(0), 11);
        totalCounts.put(rects.get(1), 5);
        totalCounts.put(rects.get(2), 5);
        totalCounts.put(rects.get(3), 5);

        totalCounts.put(dividedRects.get(0), 3);
        totalCounts.put(dividedRects.get(1), 3);
        totalCounts.put(dividedRects.get(2), 3);
        totalCounts.put(dividedRects.get(3), 2);
    }

    public static boolean isRearranged(Rect rect) {
        return isRearranged(totalCounts.get(rect));
    }

    public static boolean isRearranged(int totalCount) {
        return totalCount > MAX_PAGEABLE_COUNT * MAX_DOCUMENT_COUNT;
    }

    public static KakaoPlaceDto createPlaceDto(int totalCount, int pageableCount) {
        Meta meta = createMeta(totalCount, pageableCount);
        List<Document> documents = createDocuments(createDocumentCount(totalCount));
        return new KakaoPlaceDto(meta, documents);
    }

    public static Meta createMeta(int totalCount, int pageableCount) {
        return Meta.builder()
                .pageableCount(pageableCount)
                .totalCount(totalCount)
                .isEnd(false)
                .sameName(null)
                .build();
    }

    public static List<Document> createDocuments(int documentCount) {
        List<Document> documents = new ArrayList<>();
        for (int i = 0; i < documentCount; i++) {
            documents.add(new Document());
        }
        return documents;
    }

    public static int createDocumentCount(int totalCount) {
        if (isRearranged(totalCount)) {
            return MAX_PAGEABLE_COUNT * MAX_DOCUMENT_COUNT;
        }
        return totalCount;
    }

    public static int createPageableCount(int totalCount) {
        int pageableCount = totalCount / MAX_DOCUMENT_COUNT;
        if (totalCount % MAX_DOCUMENT_COUNT > 0) {
            pageableCount++;
        }
        return Math.min(pageableCount, MAX_PAGEABLE_COUNT);
    }
}
