package com.songpapeople.hashtagmap.district.service;

import com.songpapeople.hashtagmap.district.service.dto.DistrictSaveDto;
import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.CommonExceptionStatus;
import com.songpapeople.hashtagmap.place.domain.model.District;
import com.songpapeople.hashtagmap.place.domain.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DistrictCommandService {
    private final DistrictRepository districtRepository;

    @Transactional
    public Long saveDistrict(DistrictSaveDto districtSaveDto) {
        District district = new District(districtSaveDto.getDistrictName());

        boolean present = districtRepository.findByDistrictName(district.getDistrictName()).isPresent();
        if (present) {
            throw new AdminException(
                    CommonExceptionStatus.ALREADY_PERSIST,
                    String.format("%s : 이미 존재하는 지역 이름입니다.", district.getDistrictName())
            );
        }

        district = districtRepository.save(district);
        return district.getId();
    }
}
