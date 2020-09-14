package com.songpapeople.hashtagmap.event.process;

import com.songpapeople.hashtagmap.event.message.Event;
import com.songpapeople.hashtagmap.event.message.KakaoEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EventTypeTest {

    @DisplayName("정의된 이벤트 타입을 가져온다.")
    @Test
    void getTypes() {
        List<Class<? extends Event>> types = EventType.getTypes();

        assertThat(types).contains(KakaoEvent.class);
    }

    @DisplayName("이벤트 클래스로 이벤트 타입을 가져온다.")
    @Test
    void findBy() {
        EventType eventType = EventType.findBy(KakaoEvent.class);

        assertThat(eventType).isEqualTo(EventType.KAKAO);
    }

    @DisplayName("존재하지 않는 이벤트로 이벤트 타입을 찾으려하는 경우 Exception")
    @Test
    void findByException() {
        assertThatThrownBy(() -> EventType.findBy(Event.class))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("처리할 수 없는 이벤트 타입 : %s", Event.class.getSimpleName());
    }

    @DisplayName("해당 이벤트의 클래스 이름을 가져온다.")
    @Test
    void getTypeName() {
        String typeName = EventType.KAKAO.getTypeName();

        assertThat(typeName).isEqualTo(KakaoEvent.class.getSimpleName());
    }
}