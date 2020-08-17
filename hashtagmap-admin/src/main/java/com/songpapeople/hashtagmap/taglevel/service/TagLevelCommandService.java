package com.songpapeople.hashtagmap.taglevel.service;

import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.AdminExceptionStatus;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.taglevel.domain.TagLevel;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagLevelCommandService {
    private final InstagramRepository instagramRepository;
    private final TagLevelRepository tagLevelRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void update() {
        List<TagLevel> tagLevels = tagLevelRepository.findAll();
        validateTagLevelNotEmpty(tagLevels);

        int tagLevelSize = tagLevels.size();

        List<List<Long>> tiledHashtagCount = instagramRepository.findTiledHashtagCount(tagLevelSize);
        for (int i = 0; i < tagLevelSize; i++) {
            tagLevels.get(i).update(tiledHashtagCount.get(i));
        }
        tagLevelRepository.saveAll(tagLevels); // todo 이거 없어도 되야 하는 거 아님?
    }

    public void create() {
        tagLevelRepository.save(new TagLevel());
        update();
    }

    public void delete() {
        List<TagLevel> tagLevels = tagLevelRepository.findAll();
        validateTagLevelNotEmpty(tagLevels);

        tagLevelRepository.delete(tagLevels.get(tagLevels.size() - 1));
        update();
    }

    private void validateTagLevelNotEmpty(List<TagLevel> tagLevels) {
        if (tagLevels.isEmpty()) {
            log.error("AdminException:" + "테그레벨이 존재하지 않습니다.");
            throw new AdminException(AdminExceptionStatus.NOT_FOUND_TAG_LEVEL);
        }
    }
}
