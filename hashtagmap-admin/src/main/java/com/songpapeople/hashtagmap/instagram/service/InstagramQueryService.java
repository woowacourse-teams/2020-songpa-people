package com.songpapeople.hashtagmap.instagram.service;

import com.songpapeople.hashtagmap.blacklist.service.dto.AbnormalInstagramDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InstagramQueryService {
    public static final int SUB_BLACK_LIST_SIZE = 20;

    private final InstagramRepository instagramRepository;

    public List<AbnormalInstagramDto> findSemiBlackListInstagram() {
        List<Instagram> instagrams = instagramRepository.findAllOrderByHashtagCountAndLimitBy(SUB_BLACK_LIST_SIZE);
        return instagrams.stream()
                .map(AbnormalInstagramDto::of)
                .collect(Collectors.toList());
    }
}
