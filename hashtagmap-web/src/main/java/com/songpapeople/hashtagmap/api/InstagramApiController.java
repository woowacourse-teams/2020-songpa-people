package com.songpapeople.hashtagmap.api;

import com.songpapeople.hashtagmap.dto.InstagramPostResponse;
import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.service.InstagramPostQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/instagram")
public class InstagramApiController {
    private final InstagramPostQueryService instagramPostQueryService;

    public InstagramApiController(InstagramPostQueryService instagramPostQueryService) {
        this.instagramPostQueryService = instagramPostQueryService;
    }

    @GetMapping("/{id}/post")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<List<InstagramPostResponse>> getInstagramPost(@PathVariable Long id) {
        List<InstagramPostResponse> instagramPostResponses = instagramPostQueryService.findAllByInstagramId(id);
        return CustomResponse.of(instagramPostResponses);
    }
}
