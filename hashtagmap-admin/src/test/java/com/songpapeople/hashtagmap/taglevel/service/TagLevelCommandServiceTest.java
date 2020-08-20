package com.songpapeople.hashtagmap.taglevel.service;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.taglevel.domain.TagLevel;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class TagLevelCommandServiceTest {
    @Autowired
    private TagLevelCommandService tagLevelCommandService;

    @Autowired
    private TagLevelRepository tagLevelRepository;

    @Autowired
    private InstagramRepository instagramRepository;

    @BeforeEach
    private void setUp() {
        List<Instagram> instagrams = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            instagrams.add(Instagram.builder()
                    .hashtagCount(100L + i)
                    .build());
        }
        instagramRepository.saveAll(instagrams);
    }

    @DisplayName("TagLevel 정보를 갱신한다.")
    @Test
    public void updateTagLevelTest() {
        // given
        List<TagLevel> tagLevels = Arrays.asList(new TagLevel(), new TagLevel());
        tagLevelRepository.saveAll(tagLevels);

        // when
        tagLevelCommandService.update();

        // then
        List<TagLevel> actual = tagLevelRepository.findAll();
        assertAll("TagLevel 최소/최대 hashtag count",
                () -> assertThat(actual.get(0).getMinHashtagCount()).isEqualTo(100),
                () -> assertThat(actual.get(0).getMaxHashtagCount()).isEqualTo(104),
                () -> assertThat(actual.get(1).getMinHashtagCount()).isEqualTo(105),
                () -> assertThat(actual.get(1).getMaxHashtagCount()).isEqualTo(109)
        );
    }

    @AfterEach
    private void tearDown() {
        tagLevelRepository.deleteAll();
        instagramRepository.deleteAll();
    }
}