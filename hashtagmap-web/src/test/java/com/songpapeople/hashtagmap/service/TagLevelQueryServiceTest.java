package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.TagLevelResponse;
import com.songpapeople.hashtagmap.taglevel.model.TagLevel;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TagLevelQueryServiceTest {
    @Mock
    private TagLevelRepository tagLevelRepository;

    private TagLevelQueryService tagLevelQueryService;

    @BeforeEach
    private void setUp() {
        tagLevelQueryService = new TagLevelQueryService(tagLevelRepository);
    }

    @DisplayName("태그 레벨을 5개 가져오는지 테스트")
    @Test
    void findTagLevelsTest() {
        List<TagLevel> tagLevels = Arrays.asList(
                new TagLevel(1L, 100L, 105L),
                new TagLevel(2L, 106L, 110L),
                new TagLevel(3L, 111L, 115L),
                new TagLevel(4L, 116L, 120L),
                new TagLevel(5L, 121L, 125L)
        );
        when(tagLevelRepository.findFiveByModifiedDateOrderById()).thenReturn(tagLevels);

        List<TagLevelResponse> tagLevelResponses = tagLevelQueryService.findTagLevels();

        assertThat(tagLevelResponses).hasSize(5);
    }
}
