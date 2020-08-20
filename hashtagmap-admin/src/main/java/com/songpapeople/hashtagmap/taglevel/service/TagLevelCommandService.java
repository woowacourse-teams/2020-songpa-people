package com.songpapeople.hashtagmap.taglevel.service;

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

    private TagLevelFactory tagLevelFactory = new TagLevelFactory();

    @Transactional
    public void update() {
        List<TagLevel> tagLevels = tagLevelRepository.findAll();
        List<Long> hashtagCounts = instagramRepository.findAllHashtagCountByOrderAsc();
        tagLevelFactory.update(hashtagCounts, tagLevels);
    }
}
