package com.songpapeople.hashtagmap.api;

import com.songpapeople.hashtagmap.dto.InstagramPostResponse;
import com.songpapeople.hashtagmap.response.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instagram")
public class InstagramApiController {

    @GetMapping("/{id}/post")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<InstagramPostResponse> getInstagramPost(@PathVariable Long id) {
        return CustomResponse.of(InstagramPostResponse.of());
    }
}
