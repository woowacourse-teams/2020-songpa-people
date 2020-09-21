package com.songpapeople.hashtagmap.event.process;

import com.songpapeople.hashtagmap.event.config.EventConfiguration;
import com.songpapeople.hashtagmap.event.message.KakaoEvent;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

class EventConsumerTest {
    private final Point point = new Point("34", "124");
    private final District district = new District("서울시 강남구");
    private final Zone zone = new Zone(point, point, district, true);

    @DisplayName("이벤트 타입에 맞는 이벤트 소모 테스트")
    @Test
    void consumeTest() throws InterruptedException {
        //given
        CountDownLatch countDownLatch = new CountDownLatch(1);
        KakaoEvent kakaoEvent = new KakaoEvent((event) -> countDownLatch.countDown(), Category.CAFE, zone);
        EventConfiguration eventConfiguration = new EventConfiguration();
        EventBrokerGroup eventBrokerGroup = eventConfiguration.eventBrokers();

        //when
        EventConsumer eventConsumer = eventConfiguration.eventConsumer(eventBrokerGroup);
        eventBrokerGroup.push(kakaoEvent);

        //then
        countDownLatch.await();
        eventConsumer.stop();
        assertThat(countDownLatch.getCount()).isEqualTo(0);
    }
}