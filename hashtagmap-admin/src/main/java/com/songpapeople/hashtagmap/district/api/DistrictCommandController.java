package com.songpapeople.hashtagmap.district.api;

import com.songpapeople.hashtagmap.district.service.DistrictCommandService;
import com.songpapeople.hashtagmap.district.service.dto.DistrictDeleteDto;
import com.songpapeople.hashtagmap.district.service.dto.DistrictSaveDto;
import com.songpapeople.hashtagmap.district.service.dto.DistrictUpdateDto;
import com.songpapeople.hashtagmap.response.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/districts")
public class DistrictCommandController {
    private final DistrictCommandService districtCommandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Long> saveDistrict(@RequestBody @Valid DistrictSaveDto districtSaveDto) {
        return CustomResponse.of(districtCommandService.saveDistrict(districtSaveDto));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<Void> deleteDistricts(@RequestBody @Valid DistrictDeleteDto districtDeleteDto) {
        districtCommandService.deleteDistricts(districtDeleteDto);
        return CustomResponse.empty();
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<Void> updateDistrict(@RequestBody @Valid DistrictUpdateDto districtUpdateDto) {
        districtCommandService.updateDistrict(districtUpdateDto);
        return CustomResponse.empty();
    }

}