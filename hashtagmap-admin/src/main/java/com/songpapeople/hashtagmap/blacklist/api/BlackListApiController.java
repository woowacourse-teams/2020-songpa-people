package com.songpapeople.hashtagmap.blacklist.api;

import com.songpapeople.hashtagmap.blacklist.service.BlackListCommandService;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListRequest;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListResponse;
import com.songpapeople.hashtagmap.blacklist.service.dto.SemiBlackListDto;
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

    @GetMapping("/semi")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<List<SemiBlackListDto>> getSemiBlackList() {
        return CustomResponse.of(instagramQueryService.findSemiBlackListInstagram());
    }

    // TODO: 2020/08/24 put/delete/instagram
    @PostMapping("/update-instagram")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<BlackListResponse> updateInstagramAfterAddBlackList(@RequestBody BlackListRequest blackListReq) {
        blackListCommandService.save(BlackListRequest.toBlackList(blackListReq));
        String replaceName = blackListReq.getReplaceName();
        BlackListResponse blackListResponse =
                BlackListResponse.of(instagramScheduleService.updateInstagramByBlackList(replaceName, blackListReq.getPlaceId()));
        return CustomResponse.of(blackListResponse);
    }

    @PostMapping("/delete-instagram")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CustomResponse<Void> deleteInstagramAfterAddBlackList(@RequestBody BlackListRequest blackListRequest) {
        blackListCommandService.save(BlackListRequest.toSkipBlackList(blackListRequest));
        instagramPostCommandService.deleteByInstagramId(blackListRequest);
        instagramCommandService.delete(blackListRequest);
        return CustomResponse.empty();
    }
}
