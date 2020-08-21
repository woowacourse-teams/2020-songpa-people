package com.songpapeople.hashtagmap.taglevel.api;

import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.taglevel.service.TagLevelCommandService;
import com.songpapeople.hashtagmap.taglevel.service.TagLevelQueryService;
import com.songpapeople.hashtagmap.taglevel.service.dto.TagLevelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tag-levels")
public class TagLevelApiController {
    private final TagLevelCommandService tagLevelCommandService;
    private final TagLevelQueryService tagLevelQueryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<List<TagLevelDto>> findAll() {
        List<TagLevelDto> tagLevelDtos = tagLevelQueryService.findAll();
        return CustomResponse.of(tagLevelDtos);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<Void> update() {
        tagLevelCommandService.update();
        return CustomResponse.empty();
    }
}
