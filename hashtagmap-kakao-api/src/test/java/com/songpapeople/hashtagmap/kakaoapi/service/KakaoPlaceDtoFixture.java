package com.songpapeople.hashtagmap.kakaoapi.service;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Document;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.dto.Meta;

import java.util.ArrayList;
import java.util.List;

public class KakaoPlaceDtoFixture {
    public static final int MAX_PAGEABLE_COUNT = 45;
    private static final int MAX_DOCUMNET_COUNT = 5;


    public static KakaoPlaceDto createNormalPlaceDto(int documentCount, int totalCount) {
        Meta meta = createMeta(totalCount);
        List<Document> documents = createDocuments(documentCount);
        return new KakaoPlaceDto(meta, documents);
    }

    public static Meta createMeta(int totalCount) {
        return Meta.builder()
                .pageableCount(MAX_PAGEABLE_COUNT)
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
}
