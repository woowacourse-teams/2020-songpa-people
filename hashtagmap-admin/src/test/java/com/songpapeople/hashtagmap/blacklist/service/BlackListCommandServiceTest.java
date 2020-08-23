package com.songpapeople.hashtagmap.blacklist.service;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class BlackListCommandServiceTest {
    @Autowired
    private BlackListCommandService blackListCommandService;

    @Autowired
    private BlackListRepository blackListRepository;

    @DisplayName("blackList를 저장하는 기능 테스트 - 처음 저장")
    @Test
    void save() {
        BlackList first = new BlackList(1L, "first");

        blackListCommandService.save(first);

        BlackList result = blackListRepository.findByPlaceId(1L).get();
        assertThat(result.getReplaceName()).isEqualTo(first.getReplaceName());
    }

    @DisplayName("blackList를 저장하는 기능 테스트 - 덮어쓰기")
    @Test
    void saveWhenUpdate() {
        long placeId = 100L;
        BlackList first = new BlackList(placeId, "first");
        blackListRepository.save(first);

        BlackList second = new BlackList(placeId, "second");
        second.setSkipPlace(true);
        blackListCommandService.save(second);

        BlackList result = blackListRepository.findByPlaceId(placeId).get();
        assertAll(
                () -> assertThat(result.getReplaceName()).isEqualTo(second.getReplaceName()),
                () -> assertThat(result.getIsSkipPlace()).isEqualTo(second.getIsSkipPlace())
        );
    }

    @AfterEach
    void tearDown() {
        blackListRepository.deleteAll();
    }
}
