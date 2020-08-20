package com.songpapeople.hashtagmap.instagram.service;

import com.songpapeople.hashtagmap.blacklist.service.dto.SubBlackListDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InstagramQueryService {
    public static final int SUB_BLACK_LIST_SIZE = 20;

    private final InstagramRepository instagramRepository;

    public InstagramQueryService(InstagramRepository instagramRepository) {
        this.instagramRepository = instagramRepository;
    }

    @Transactional
    public List<SubBlackListDto> findSubBlackListInstagram() {
        List<Instagram> instagrams = instagramRepository.findAll();
        return instagrams.stream()
                .sorted(Comparator.comparingDouble(Instagram::getHashtagCount).reversed())
                .limit(SUB_BLACK_LIST_SIZE)
                .map(SubBlackListDto::of)
                .collect(Collectors.toList());
    }
}
