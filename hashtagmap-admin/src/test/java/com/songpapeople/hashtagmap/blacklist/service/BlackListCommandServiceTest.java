package com.songpapeople.hashtagmap.blacklist.service;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BlackListCommandServiceTest {
    @Autowired
    private BlackListCommandService blackListCommandService;

    @Autowired
    private BlackListRepository blackListRepository;

    @DisplayName("blackList를 저장하는 기능 테스트 - 처음 저장")
    @Test
    void save() {
        BlackList first = new BlackList(1L,"first");

        blackListCommandService.save(first);

        BlackList result = blackListRepository.findByPlaceId(1L).get();
        assertThat(result.getReplaceName()).isEqualTo(first.getReplaceName());
    }

    @DisplayName("blackList를 저장하는 기능 테스트 - 덮어쓰기")
    @Test
    void saveWhenUpdate() {
        BlackList first = new BlackList(1L,"first");
        blackListRepository.save(first);

        BlackList seceond = new BlackList(1L,"seceond");
        blackListCommandService.save(seceond);

        BlackList reesult = blackListRepository.findByPlaceId(1L).get();
        assertThat(reesult.getReplaceName()).isEqualTo(seceond.getReplaceName());
    }
}
