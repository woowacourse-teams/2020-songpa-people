package com.songpapeople.hashtagmap.blacklist.api;

import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListAddRequest;
import com.songpapeople.hashtagmap.blacklist.service.dto.SubBlackListDto;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.service.InstagramQueryService;
import com.songpapeople.hashtagmap.response.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
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

    @GetMapping
    @RequestMapping("/sub")
    public CustomResponse<List<SubBlackListDto>> getSubBlackList(){
        return CustomResponse.of(instagramQueryService.findSubBlackListInstagram());
    }

    @PostMapping
    public CustomResponse<Void> addBlackList(@RequestBody BlackListAddRequest blackListRequest) {
        System.out.println(blackListRequest.getOriginName());
        System.out.println(blackListRequest.getReplaceName());
        return CustomResponse.empty();
    }
}
