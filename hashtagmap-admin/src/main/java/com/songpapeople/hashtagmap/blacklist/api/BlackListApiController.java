package com.songpapeople.hashtagmap.blacklist.api;

import com.songpapeople.hashtagmap.blacklist.service.BlackListCommandService;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListAddRequest;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListAddResponse;
import com.songpapeople.hashtagmap.blacklist.service.dto.SubBlackListDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
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
        Instagram instagramUpdated = updateInstagram(blackListRequest);
        return CustomResponse.of(BlackListAddResponse.of(instagramUpdated));
    }

    private Instagram updateInstagram(@RequestBody BlackListAddRequest blackListRequest) {
        Instagram instagramToUpdate = instagramQueryService.findByPlaceId(blackListRequest.getPlaceId());
        String replaceName = blackListRequest.getReplaceName();
        return instagramScheduleService.updateBlackLists(replaceName, instagramToUpdate);
    }
}
