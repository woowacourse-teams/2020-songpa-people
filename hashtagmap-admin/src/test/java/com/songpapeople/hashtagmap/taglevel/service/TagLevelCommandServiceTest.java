package com.songpapeople.hashtagmap.taglevel.service;

import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.taglevel.domain.TagLevel;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class TagLevelCommandServiceTest {
    @Autowired
    private TagLevelRepository tagLevelRepository;

    @Autowired
    private TagLevelCommandService tagLevelCommandService;

    @MockBean
    private InstagramRepository instagramRepository;


    @DisplayName("TagLevel 정보를 갱신한다.")
    @Test
    public void updateTagLevelTest() {
        // given
        List<TagLevel> tagLevels = Arrays.asList(
                new TagLevel(1L, null, null),
                new TagLevel(2L, null, null),
                new TagLevel(3L, null, null)
        );
        tagLevelRepository.saveAll(tagLevels);

        when(instagramRepository.findTiledHashtagCount(anyInt()))
                .thenReturn(
                        Arrays.asList(
                                Arrays.asList(100L, 110L),
                                Arrays.asList(111L, 120L),
                                Arrays.asList(121L, 130L)
                        )
                );

        // when
        tagLevelCommandService.updateTagLevels();

        // then
        List<TagLevel> actual = tagLevelRepository.findAll();
        assertThat(actual.get(0).getMinHashtagCount()).isEqualTo(100);
        assertThat(actual.get(0).getMaxHashtagCount()).isEqualTo(110);
        assertThat(actual.get(1).getMinHashtagCount()).isEqualTo(111);
        assertThat(actual.get(1).getMaxHashtagCount()).isEqualTo(120);
        assertThat(actual.get(2).getMinHashtagCount()).isEqualTo(121);
        assertThat(actual.get(2).getMaxHashtagCount()).isEqualTo(130);
    }

    @DisplayName("TagLevel 정보를 갱신할 때, TagLevel이 존재하지 않을 경우 예외 처리")
    @Test
    public void tagLevelNotExistTest() {
        assertThatThrownBy(() -> tagLevelCommandService.updateTagLevels())
                .hasMessage("TagLevel이 존재하지 않습니다.");
    }

    @AfterEach
    private void tearDown() {
        tagLevelRepository.deleteAll();
    }
}