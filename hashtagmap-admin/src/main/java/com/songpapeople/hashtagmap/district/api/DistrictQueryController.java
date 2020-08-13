package com.songpapeople.hashtagmap.district.api;

import com.songpapeople.hashtagmap.district.service.DistrictQueryService;
import com.songpapeople.hashtagmap.district.service.dto.DistrictDto;
import com.songpapeople.hashtagmap.district.service.dto.ZoneDto;
import com.songpapeople.hashtagmap.response.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/districts")
public class DistrictQueryController {
    private final DistrictQueryService districtQueryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<List<DistrictDto>> getAllDistrict() {
        return CustomResponse.of(districtQueryService.getAllDistrict());
    }

    @GetMapping("/names")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<List<String>> getAllDistrictName() {
        return CustomResponse.of(districtQueryService.getAllDistrictName());
    }

    @GetMapping("/zones")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<List<ZoneDto>> getAllZone() {
        return CustomResponse.of(districtQueryService.getAllZone());
    }
}
