package com.songpapeople.hashtagmap.event.message;

import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KakaoEventTest {

    @DisplayName("카테고리 그룹 코드 반환")
    @Test
    void getCategoryGroupCode() {
        //given
        KakaoEvent kakaoEvent = new KakaoEvent((e) -> {
        }, Category.CAFE, Zone.builder().build());

        //when
        String categoryGroupCode = kakaoEvent.getCategoryGroupCode();

        //then
        assertThat(categoryGroupCode).isEqualTo(Category.CAFE.getCategoryGroupCode());
    }

    @DisplayName("KakaoEventHistory 엔티티의 id를 주입한다.")
    @Test
    void placeId() {
        //given
        KakaoEvent kakaoEvent = new KakaoEvent((e) -> {
        }, Category.CAFE, Zone.builder().build());

        //when
        kakaoEvent.placeId(1L);

        //then
        assertThat(kakaoEvent.getEventHistoryId()).isEqualTo(1L);
    }
}