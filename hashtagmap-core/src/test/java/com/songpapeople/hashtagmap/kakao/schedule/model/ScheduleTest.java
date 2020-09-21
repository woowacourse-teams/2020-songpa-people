package com.songpapeople.hashtagmap.kakao.schedule.model;

import com.songpapeople.hashtagmap.config.vo.Flag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ScheduleTest {

    @DisplayName("flag toggle 기능 테스트")
    @ParameterizedTest
    @CsvSource(value = {"Y,false", "N,true"})
    void toggle(Flag flag, boolean expect) {
        Schedule schedule = new Schedule("KAKAO", "name", flag);

        schedule.toggle();

        assertThat(schedule.isActive()).isEqualTo(expect);
    }
}