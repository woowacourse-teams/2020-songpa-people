package com.songpapeople.hashtagmap.blacklist.service;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import com.songpapeople.hashtagmap.blacklist.domain.repsitory.BlackListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BlackListCommandService {
    private final BlackListRepository blackListRepository;

    public BlackListCommandService(BlackListRepository blackListRepository) {
        this.blackListRepository = blackListRepository;
    }

    @Transactional
    public BlackList save(BlackList blackList) {
        Optional<BlackList> blackListByPlaceId = blackListRepository.findByPlaceId(blackList.getPlaceId());
        blackListByPlaceId.ifPresent(item -> {
            item.setReplaceName(blackList.getReplaceName());
            item.setSkipPlace(blackList.getIsSkipPlace());
        });
        return blackListRepository.save(blackListByPlaceId.orElseGet(() -> blackList));
    }
}
