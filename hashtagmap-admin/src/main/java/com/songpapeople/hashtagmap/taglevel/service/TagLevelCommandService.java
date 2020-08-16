package com.songpapeople.hashtagmap.taglevel.service;

import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.AdminExceptionStatus;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.taglevel.domain.TagLevel;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagLevelCommandService {
    private final InstagramRepository instagramRepository;
    private final TagLevelRepository tagLevelRepository;

    @Transactional
    public void update() {
        List<TagLevel> tagLevels = tagLevelRepository.findAll();
        if (tagLevels.isEmpty()) {
            log.error("AdminException:" + "테그레벨이 존재하지 않아 갱신할 수 없습니다.");
            throw new AdminException(AdminExceptionStatus.NOT_FOUND_TAG_LEVEL,
                    "테그레벨이 존재하지 않아 갱신할 수 없습니다.");
        }

        int tagLevelSize = tagLevels.size();
        List<List<Long>> tiledHashtagCount = instagramRepository.findTiledHashtagCount(tagLevelSize);
        if (tiledHashtagCount.size() != tagLevels.size()) {
            log.error("AdminException:" + "테그레벨을 갱신할 수 없습니다.");
            throw new AdminException(AdminExceptionStatus.INVALID_TAG_LEVEL_UPDATE,
                    "Query의 TileSize와 TagLevelSize 개수가 맞지 않아 갱신할 수 없습니다.");
        }

        for (int i = 0; i < tagLevelSize; i++) {
            tagLevels.get(i).update(tiledHashtagCount.get(i));
        }
    }

    public void create() {
        tagLevelRepository.save(new TagLevel());
        update();
    }

    public void delete() {
        long count = tagLevelRepository.count();
        if (count <= 0) {
            log.error("AdminException:" + "테그레벨이 존재하지 않아 삭제할 수 없습니다.");
            throw new AdminException(AdminExceptionStatus.NOT_FOUND_TAG_LEVEL,
                    "테그레벨이 존재하지 않아 삭제할 수 없습니다.");
        }
        tagLevelRepository.deleteById(count);
        update();
    }
}
