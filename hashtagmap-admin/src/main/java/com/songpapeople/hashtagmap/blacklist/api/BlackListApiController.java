package com.songpapeople.hashtagmap.blacklist.api;

import com.songpapeople.hashtagmap.blacklist.service.BlackListCommandService;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListRequest;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListResponse;
import com.songpapeople.hashtagmap.blacklist.service.dto.SemiBlackListDto;
import com.songpapeople.hashtagmap.instagram.service.InstagramQueryService;
import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.scheduler.InstagramScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/blacklist")
public class BlackListApiController {
    private final InstagramQueryService instagramQueryService;
    private final BlackListCommandService blackListCommandService;
    private final InstagramScheduleService instagramScheduleService;

    @GetMapping("/semi")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<List<SemiBlackListDto>> getSemiBlackList() {
        return CustomResponse.of(instagramQueryService.findSemiBlackListInstagram());
    }

    @PutMapping("/instagram")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<BlackListResponse> updateInstagramAfterAddBlackList(@RequestBody @Valid BlackListRequest blackListReq) {
        Long placeId = blackListReq.getPlaceId();
        String replaceName = blackListReq.getReplaceName();
        BlackListResponse blackListResponse =
                BlackListResponse.of(instagramScheduleService.updateInstagramByBlackList(placeId, replaceName));
        return CustomResponse.of(blackListResponse);
    }

    @DeleteMapping("/instagram")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CustomResponse<Void> deleteInstagramAfterAddBlackList(@RequestBody @Valid BlackListRequest blackListRequest) {
        blackListCommandService.deleteInstagramAfterAddBlackList(blackListRequest);
        return CustomResponse.empty();
    }
}
