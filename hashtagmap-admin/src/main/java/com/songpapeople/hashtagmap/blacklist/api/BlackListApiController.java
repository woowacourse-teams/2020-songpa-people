package com.songpapeople.hashtagmap.blacklist.api;

import com.songpapeople.hashtagmap.blacklist.service.BlackListCommandService;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListAddRequest;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListAddResponse;
import com.songpapeople.hashtagmap.blacklist.service.dto.SubBlackListDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.service.InstagramCommandService;
import com.songpapeople.hashtagmap.instagram.service.InstagramQueryService;
import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.scheduler.InstagramScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/blacklist")
public class BlackListApiController {
    private final InstagramQueryService instagramQueryService;
    private final InstagramCommandService instagramCommandService;
    private final BlackListCommandService blackListCommandService;
    private final InstagramScheduleService instagramScheduleService;

    @GetMapping
    @RequestMapping("/sub")
    public CustomResponse<List<SubBlackListDto>> getSubBlackList() {
        return CustomResponse.of(instagramQueryService.findSubBlackListInstagram());
    }

    @PostMapping
    public CustomResponse<BlackListAddResponse> addBlackList(@RequestBody BlackListAddRequest blackListRequest) {
        blackListCommandService.save(BlackListAddRequest.toBlackList(blackListRequest));
        Instagram updated = updateInstagram(blackListRequest);
        return CustomResponse.of(BlackListAddResponse.of(updated));
    }

    private Instagram updateInstagram(@RequestBody BlackListAddRequest blackListRequest) {
        Long placeId = blackListRequest.getPlaceId();
        String replaceName = blackListRequest.getReplaceName();

        Instagram instagramToUpdate = instagramQueryService.findByPlaceId(placeId);
        Long hashtagCount = instagramScheduleService.findHashtagCount(replaceName);
        return instagramCommandService.update(instagramToUpdate, replaceName, hashtagCount);
    }
}
