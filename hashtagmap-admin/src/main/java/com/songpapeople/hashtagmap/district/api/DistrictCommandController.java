package com.songpapeople.hashtagmap.district.api;

import com.songpapeople.hashtagmap.district.service.DistrictCommandService;
import com.songpapeople.hashtagmap.district.service.dto.DistrictSaveDto;
import com.songpapeople.hashtagmap.response.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/district")
public class DistrictCommandController {
    private final DistrictCommandService districtCommandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Long> saveDistrict(@RequestBody @Valid DistrictSaveDto districtSaveDto) {
        return CustomResponse.of(districtCommandService.saveDistrict(districtSaveDto));
    }

}
