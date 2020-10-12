package com.songpapeople.hashtagmap.instagram.service;

import com.songpapeople.hashtagmap.blacklist.service.dto.AbnormalInstagramDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.dto.InstagramForBlacklist;
import com.songpapeople.hashtagmap.instagram.repository.InstagramAdminQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InstagramQueryService {
    public static final int ABNORMAL_INSTAGRAM_LIST_SIZE = 20;

    private final InstagramAdminQueryRepository instagramAdminQueryRepository;

    public List<AbnormalInstagramDto> findSemiBlackListInstagram() {
        List<Instagram> instagrams = findAllOrderByHashtagCountAndLimitBy(ABNORMAL_INSTAGRAM_LIST_SIZE);
        return instagrams.stream()
                .map(AbnormalInstagramDto::of)
                .collect(Collectors.toList());
    }

    private List<Instagram> findAllOrderByHashtagCountAndLimitBy(int limit) {
        List<InstagramForBlacklist> instagramForBlacklists = instagramAdminQueryRepository.findAllOrderByHashtagCountAndLimitBy(limit);
        return instagramForBlacklists.stream()
                .map(InstagramForBlacklist::toInstagram)
                .collect(Collectors.toList());

    }
}
