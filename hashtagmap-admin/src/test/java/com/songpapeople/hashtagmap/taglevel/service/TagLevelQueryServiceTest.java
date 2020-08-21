package com.songpapeople.hashtagmap.taglevel.service;

import com.songpapeople.hashtagmap.taglevel.model.TagLevel;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import com.songpapeople.hashtagmap.taglevel.service.dto.TagLevelDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TagLevelQueryServiceTest {
    @Autowired
    private TagLevelQueryService tagLevelQueryService;

    @Autowired
    private TagLevelRepository tagLevelRepository;

    @DisplayName("TagLevel의 정보를 조회한다.")
    @Test
    public void findAllTest() {
        tagLevelRepository.saveAll(Arrays.asList(
                new TagLevel(1L, 100L, 200L),
                new TagLevel(2L, 201L, 300L)
        ));

        List<TagLevelDto> tagLevelDtos = tagLevelQueryService.findAll();
        assertThat(tagLevelDtos.size()).isEqualTo(2);
    }
}