package com.songpapeople.hashtagmap.blacklist.api;

import com.songpapeople.hashtagmap.blacklist.domain.model.BlackList;
import com.songpapeople.hashtagmap.blacklist.service.BlackListCommandService;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListRequest;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListResponse;
import com.songpapeople.hashtagmap.blacklist.service.dto.SemiBlackListDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.service.InstagramCommandService;
import com.songpapeople.hashtagmap.instagram.service.InstagramQueryService;
import com.songpapeople.hashtagmap.instagrampost.service.InstagramPostCommandService;
import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.scheduler.InstagramScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/blacklist")
public class BlackListApiController {
    private final InstagramQueryService instagramQueryService;
    private final InstagramCommandService instagramCommandService;
    private final InstagramPostCommandService instagramPostCommandService;
    private final BlackListCommandService blackListCommandService;
    private final InstagramScheduleService instagramScheduleService;

    @GetMapping
    @RequestMapping("/semi")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<List<SemiBlackListDto>> getSemiBlackList() {
        return CustomResponse.of(instagramQueryService.findSemiBlackListInstagram());
    }

    @PostMapping
    @RequestMapping("/update-instagram")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<BlackListResponse> addBlackList(@RequestBody BlackListRequest blackListRequest) {
        blackListCommandService.save(BlackListRequest.toBlackList(blackListRequest));
        Instagram instagramUpdated = updateInstagramAndPost(blackListRequest);
        return CustomResponse.of(BlackListResponse.of(instagramUpdated));
    }

    private Instagram updateInstagramAndPost(@RequestBody BlackListRequest blackListRequest) {
        Instagram instagramToUpdate = instagramQueryService.findByPlaceId(blackListRequest.getPlaceId());
        String replaceName = blackListRequest.getReplaceName();
        return instagramScheduleService.updateBlackList(replaceName, instagramToUpdate);
    }

    @PostMapping
    @RequestMapping("/delete-instagram")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CustomResponse<Void> deleteInstagramAndPost(@RequestBody BlackListRequest blackListRequest) {
        BlackList blackList = BlackListRequest.toBlackList(blackListRequest);
        blackList.setSkipPlace(true);
        blackListCommandService.save(blackList);

        Instagram instagramToDelete = instagramQueryService.findByPlaceId(blackListRequest.getPlaceId());
        instagramPostCommandService.deleteByInstagramId(instagramToDelete.getId());
        instagramCommandService.delete(instagramToDelete);
        return CustomResponse.empty();
    }
}
