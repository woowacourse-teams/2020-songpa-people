package com.songpapeople.hashtagmap.api;

import com.songpapeople.hashtagmap.dto.MarkerResponse;
import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.service.InstagramQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/maps")
@RestController
public class MapApiController {
    private final InstagramQueryService instagramQueryService;

    public MapApiController(InstagramQueryService instagramQueryService) {
        this.instagramQueryService = instagramQueryService;
    }

    @GetMapping("/markers")
    public CustomResponse<List<MarkerResponse>> findAllMarkers() {
        return CustomResponse.of(instagramQueryService.findAll());
    }
}
