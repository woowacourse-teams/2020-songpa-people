package com.songpapeople.hashtagmap.kakaoapi.domain.caller;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;

public interface KakaoApiCaller {
    KakaoPlaceDto findPlaceByCategory(String category, Rect rect);

    KakaoPlaceDto findPlaceByCategory(String category, Rect rect, int page);
}
