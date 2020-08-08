package com.songpapeople.hashtagmap.api;

import com.songpapeople.hashtagmap.dto.MarkerResponse;
import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.service.InstagramQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MapApiController {
    private final InstagramQueryService instagramQueryService;

    public MapApiController(InstagramQueryService instagramQueryService) {
        this.instagramQueryService = instagramQueryService;
    }

    @GetMapping("/markers")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<List<MarkerResponse>> findAllMarkers() {
        return CustomResponse.of(instagramQueryService.findAllMarkers());
    }
}
