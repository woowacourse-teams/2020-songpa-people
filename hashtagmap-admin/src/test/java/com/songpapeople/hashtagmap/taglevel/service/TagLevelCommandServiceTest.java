package com.songpapeople.hashtagmap.taglevel.service;

import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.AdminExceptionStatus;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        List<TagLevel> tagLevels = Arrays.asList(new TagLevel(), new TagLevel(), new TagLevel());
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
        tagLevelCommandService.update();

        // then
        List<TagLevel> actual = tagLevelRepository.findAll();
        assertThat(actual.get(0).getMinHashtagCount()).isEqualTo(100);
        assertThat(actual.get(0).getMaxHashtagCount()).isEqualTo(110);
        assertThat(actual.get(1).getMinHashtagCount()).isEqualTo(111);
        assertThat(actual.get(1).getMaxHashtagCount()).isEqualTo(120);
        assertThat(actual.get(2).getMinHashtagCount()).isEqualTo(121);
        assertThat(actual.get(2).getMaxHashtagCount()).isEqualTo(130);
    }

    @DisplayName("TagLevel이 없을 때 갱신하는 예외")
    @Test
    public void tagLevelUpdateNotExistExceptionTest() {
        AdminException exception = assertThrows(AdminException.class, () -> tagLevelCommandService.update());
        assertThat(exception.getErrorCode()).isEqualTo(AdminExceptionStatus.NOT_FOUND_TAG_LEVEL.getCode());
        assertThat(exception.getMessage()).isEqualTo("테그레벨이 존재하지 않아 갱신할 수 없습니다.");
    }

    @DisplayName("TagLevel을 추가한다.")
    @Test
    public void create() {
        when(instagramRepository.findTiledHashtagCount(anyInt()))
                .thenReturn(Arrays.asList(Arrays.asList(100L, 130L)));

        tagLevelCommandService.create();
        List<TagLevel> tagLevels = tagLevelRepository.findAll();
        assertThat(tagLevels.size()).isEqualTo(1);
    }

    @DisplayName("TagLevel을 삭제한다.")
    @Test
    public void delete() {
        when(instagramRepository.findTiledHashtagCount(1))
                .thenReturn(Arrays.asList(Arrays.asList(100L, 130L)));
        when(instagramRepository.findTiledHashtagCount(2))
                .thenReturn(Arrays.asList(Arrays.asList(100L, 111L), Arrays.asList(120L, 130L)));

        tagLevelCommandService.create();
        tagLevelCommandService.create();
        tagLevelCommandService.delete();
        List<TagLevel> tagLevels = tagLevelRepository.findAll();
        assertThat(tagLevels.size()).isEqualTo(1);
    }

    @DisplayName("TagLevel이 없을 때 삭제하는 예외")
    @Test
    public void deleteException() {
        AdminException exception = assertThrows(AdminException.class, () -> tagLevelCommandService.delete());
        assertThat(exception.getErrorCode()).isEqualTo(AdminExceptionStatus.NOT_FOUND_TAG_LEVEL.getCode());
        assertThat(exception.getMessage()).isEqualTo("테그레벨이 존재하지 않아 삭제할 수 없습니다.");
    }

    @AfterEach
    private void tearDown() {
        tagLevelRepository.deleteAll();
    }
}