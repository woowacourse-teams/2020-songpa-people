package com.songpapeople.hashtagmap.taglevel.service;

import com.songpapeople.hashtagmap.taglevel.domain.TagLevel;
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
        TagLevel tagLevel1 = new TagLevel();
        tagLevel1.update(Arrays.asList(100L, 200L));
        TagLevel tagLevel2 = new TagLevel();
        tagLevel1.update(Arrays.asList(201L, 300L));

        tagLevelRepository.saveAll(Arrays.asList(tagLevel1, tagLevel2));

        List<TagLevelDto> tagLevelDtos = tagLevelQueryService.findAll();
        assertThat(tagLevelDtos.size()).isEqualTo(2);
    }
}