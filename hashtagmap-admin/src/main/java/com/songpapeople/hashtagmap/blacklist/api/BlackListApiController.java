package com.songpapeople.hashtagmap.blacklist.api;

import com.songpapeople.hashtagmap.blacklist.service.BlackListCommandService;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListAddRequest;
import com.songpapeople.hashtagmap.blacklist.service.dto.SubBlackListDto;
import com.songpapeople.hashtagmap.instagram.service.InstagramCommandService;
import com.songpapeople.hashtagmap.instagram.service.InstagramQueryService;
import com.songpapeople.hashtagmap.response.CustomResponse;
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

    @GetMapping
    @RequestMapping("/sub")
    public CustomResponse<List<SubBlackListDto>> getSubBlackList(){
        return CustomResponse.of(instagramQueryService.findSubBlackListInstagram());
    }

    @PostMapping
    public CustomResponse<Void> addBlackList(@RequestBody BlackListAddRequest blackListRequest) {
        blackListCommandService.save(BlackListAddRequest.toBlackList(blackListRequest));
        instagramCommandService.updateByBlackList(blackListRequest);
        return CustomResponse.empty();
    }
}
