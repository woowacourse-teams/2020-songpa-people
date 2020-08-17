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

    private TagLevelFactory tagLevelFactory = new TagLevelFactory();

    @Transactional(propagation = Propagation.REQUIRED)
    public void update() {
        List<TagLevel> tagLevels = tagLevelRepository.findAll();
        List<Long> hashtagCounts = instagramRepository.findAllHashtagCountByOrderAsc();

        List<TagLevel> updatedTagLevels = tagLevelFactory.update(hashtagCounts, tagLevels);
        tagLevelRepository.saveAll(updatedTagLevels); // Todo check
    }

    @Transactional
    public void create() {
        tagLevelRepository.save(new TagLevel());
        update();
    }

    @Transactional
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
