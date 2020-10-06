package com.songpapeople.hashtagmap.taglevel.repository;

import com.songpapeople.hashtagmap.taglevel.model.TagLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class TagLevelRepositoryTest {
    @Autowired
    private TagLevelRepository tagLevelRepository;

    @Autowired
    private TagLevelQueryRepository tagLevelQueryRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    private void setUp() {
        List<TagLevel> pastTagLevels = Arrays.asList(
                new TagLevel(null, 10L, 15L),
                new TagLevel(null, 16L, 20L),
                new TagLevel(null, 21L, 25L),
                new TagLevel(null, 26L, 30L),
                new TagLevel(null, 31L, 35L),
                new TagLevel(null, 40L, 45L),
                new TagLevel(null, 46L, 50L),
                new TagLevel(null, 51L, 55L),
                new TagLevel(null, 56L, 60L),
                new TagLevel(null, 61L, 65L)
        );
        tagLevelRepository.saveAll(pastTagLevels);
    }

    @Transactional
    @DisplayName("가장 최근에 업데이트 된 태그 레벨 5개를 가져오는지 테스트")
    @Test
    void findFiveByModifiedDateOrderByIdTest() {
        List<TagLevel> tagLevels = tagLevelRepository.findAll();
        IntStream.range(5, 10)
                .forEach(index -> {
                    TagLevel tagLevel = tagLevels.get(index);
                    tagLevel.update(tagLevel.getMinHashtagCount() + 1, tagLevel.getMaxHashtagCount() + 1);
                });
        entityManager.flush();
        LocalDateTime pastModifiedDate = tagLevels.get(0).getModifiedDate();

        List<TagLevel> recentTagLevels = tagLevelQueryRepository.findFiveByModifiedDateOrderById();
        assertAll(
                () -> assertThat(recentTagLevels).hasSize(5),
                () -> assertThat(recentTagLevels.get(0).getModifiedDate().isAfter(pastModifiedDate)).isTrue()
        );
    }

    @AfterEach
    private void tearDown() {
        tagLevelRepository.deleteAll();
    }
}
