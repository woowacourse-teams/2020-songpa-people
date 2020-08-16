package com.songpapeople.hashtagmap.taglevel.service;

import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.taglevel.domain.TagLevel;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TagLevelCommandService {
    private final InstagramRepository instagramRepository;
    private final TagLevelRepository tagLevelRepository;

    @Transactional
    public void update() {
        List<TagLevel> tagLevels = tagLevelRepository.findAll();
        if (tagLevels.isEmpty()) {
            throw new IllegalArgumentException("TagLevel이 존재하지 않습니다."); // Todo custom으로
        }

        int tagLevelSize = tagLevels.size();
        List<List<Long>> tiledHashtagCount = instagramRepository.findTiledHashtagCount(tagLevelSize);
        if (tiledHashtagCount.size() != tagLevels.size()) {
            throw new IllegalArgumentException("TagLevel을 갱신할 수 없습니다.");
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
        tagLevelRepository.deleteById(tagLevelRepository.count());
        update();
    }
}
