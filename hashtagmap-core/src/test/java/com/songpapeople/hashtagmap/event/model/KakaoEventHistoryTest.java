package com.songpapeople.hashtagmap.event.model;

import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KakaoEventHistoryTest {
    private final Point point = new Point("34", "124");
    private final District district = new District("서울시 강남구");
    private final Zone zone = new Zone(point, point, district, true);

    @DisplayName("준비 상태의 History를 생성한다.")
    @Test
    void ready() {
        KakaoEventHistory kakaoEventHistory = KakaoEventHistory.ready(Category.CAFE, zone);

        assertThat(kakaoEventHistory.getEventStatus()).isEqualTo(EventStatus.READY);
    }

    @DisplayName("이벤트를 실패 상태로 전환한다.")
    @Test
    void fail() {
        //given
        KakaoEventHistory kakaoEventHistory = KakaoEventHistory.ready(Category.CAFE, zone);

        //when
        kakaoEventHistory.fail();

        //then
        assertThat(kakaoEventHistory.getEventStatus()).isEqualTo(EventStatus.FAIL);
    }

    @DisplayName("이벤트를 성공 상태로 전환한다.")
    @Test
    void success() {
        //given
        KakaoEventHistory kakaoEventHistory = KakaoEventHistory.ready(Category.CAFE, zone);

        //when
        kakaoEventHistory.success();

        //then
        assertThat(kakaoEventHistory.getEventStatus()).isEqualTo(EventStatus.SUCCESS);
    }
}