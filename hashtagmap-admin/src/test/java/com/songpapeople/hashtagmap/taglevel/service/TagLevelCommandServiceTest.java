package com.songpapeople.hashtagmap.taglevel.service;

import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.AdminExceptionStatus;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.taglevel.domain.TagLevel;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @DisplayName("TagLevel을 추가한다.")
    @TestFactory
    Collection<DynamicTest> createTest() {
        // when
        tagLevelCommandService.create();

        // then
        List<TagLevel> tagLevels = tagLevelRepository.findAll();
        return Arrays.asList(
                DynamicTest.dynamicTest("추가된 개수 확인",
                        () -> assertThat(tagLevels.size()).isEqualTo(1)),
                DynamicTest.dynamicTest("정보 갱신 여부 확인",
                        () -> {
                            assertThat(tagLevels.get(0).getMinHashtagCount()).isEqualTo(100L);
                            assertThat(tagLevels.get(0).getMaxHashtagCount()).isEqualTo(109L);
                        })
        );
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
        assertThat(actual.get(0).getMinHashtagCount()).isEqualTo(100);
        assertThat(actual.get(0).getMaxHashtagCount()).isEqualTo(104);
        assertThat(actual.get(1).getMinHashtagCount()).isEqualTo(105);
        assertThat(actual.get(1).getMaxHashtagCount()).isEqualTo(109);
    }

    @DisplayName("TagLevel을 삭제한다.")
    @TestFactory
    Collection<DynamicTest> deleteTest() {
        // when
        List<TagLevel> tagLevels = Arrays.asList(new TagLevel(1L), new TagLevel(2L), new TagLevel(3L));
        tagLevelRepository.saveAll(tagLevels);

        tagLevelCommandService.delete();

        // then
        List<TagLevel> actual = tagLevelRepository.findAll();
        return Arrays.asList(
                DynamicTest.dynamicTest("개수 확인",
                        () -> assertThat(actual.size()).isEqualTo(2)),
                DynamicTest.dynamicTest("삭제 시, id 정렬 확인",
                        () -> {
                            assertThat(actual.get(0).getId()).isLessThan(actual.get(1).getId());
                        }),
                DynamicTest.dynamicTest("정보 갱신 여부 확인",
                        () -> {
                            assertThat(actual.get(0).getMinHashtagCount()).isEqualTo(100);
                            assertThat(actual.get(0).getMaxHashtagCount()).isEqualTo(104);
                            assertThat(actual.get(1).getMinHashtagCount()).isEqualTo(105);
                            assertThat(actual.get(1).getMaxHashtagCount()).isEqualTo(109);
                        })
        );
    }

    @DisplayName("TagLevel이 없을 때 삭제하는 예외")
    @Test
    public void deleteExceptionTest() {
        AdminException exception = assertThrows(AdminException.class, () -> tagLevelCommandService.delete());
        assertThat(exception.getErrorCode()).isEqualTo(AdminExceptionStatus.NOT_FOUND_TAG_LEVEL.getCode());
        assertThat(exception.getMessage()).isEqualTo("테그레벨이 존재하지 않습니다.");
    }

    @AfterEach
    private void tearDown() {
        tagLevelRepository.deleteAll();
        instagramRepository.deleteAll();
    }
}