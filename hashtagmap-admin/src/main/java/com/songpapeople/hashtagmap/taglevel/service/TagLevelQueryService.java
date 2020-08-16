package com.songpapeople.hashtagmap.taglevel.service;

import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import com.songpapeople.hashtagmap.taglevel.service.dto.TagLevelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TagLevelQueryService {
    private final TagLevelRepository tagLevelRepository;

    public List<TagLevelDto> findAll() {
        List<TagLevelDto> tagLevels = tagLevelRepository.findAll().stream()
                .map(TagLevelDto::from)
                .collect(Collectors.toList());
        return tagLevels;
    }
}
