package com.songpapeople.hashtagmap.api;

import com.songpapeople.hashtagmap.dto.TagLevelResponse;
import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.service.TagLevelQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TagLevelApiController {
    private final TagLevelQueryService tagLevelQueryService;

    @GetMapping("/tag-levels")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<List<TagLevelResponse>> findTagLevels() {
        return CustomResponse.of(tagLevelQueryService.findTagLevels());
    }
}
