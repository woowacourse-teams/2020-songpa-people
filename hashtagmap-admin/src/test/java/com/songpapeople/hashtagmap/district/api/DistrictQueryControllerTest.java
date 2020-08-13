package com.songpapeople.hashtagmap.district.api;

import com.songpapeople.hashtagmap.district.service.DistrictQueryService;
import com.songpapeople.hashtagmap.district.service.dto.DistrictDto;
import com.songpapeople.hashtagmap.district.service.dto.ZoneDto;
import com.songpapeople.hashtagmap.docs.ApiDocument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.songpapeople.hashtagmap.docs.district.DistrictQueryDocument.getAllDistrictDoc;
import static com.songpapeople.hashtagmap.docs.district.DistrictQueryDocument.getAllDistrictNameDocs;
import static com.songpapeople.hashtagmap.docs.district.DistrictQueryDocument.getAllZoneDocs;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DistrictQueryController.class)
@ExtendWith({MockitoExtension.class})
public class DistrictQueryControllerTest extends ApiDocument {
    private static final String BASE_URI = "/districts";

    @MockBean
    private DistrictQueryService districtQueryService;

    @DisplayName("전체 District 정보 가져오기 문서화")
    @Test
    void getAllDistrict() throws Exception {
        List<DistrictDto> districtDtos = Arrays.asList(new DistrictDto(1L, "서울 송파구", LocalDateTime.now(), LocalDateTime.now(), "어드민"));
        doReturn(districtDtos).when(districtQueryService).getAllDistrict();

        this.mockMvc.perform(
                get(BASE_URI)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(getAllDistrictDoc());
    }

    @DisplayName("전체 District 이름 가져오기 문서화")
    @Test
    void getAllDistrictName() throws Exception {
        List<String> names = Arrays.asList("서울 송파구", "서울 서초구");
        doReturn(names).when(districtQueryService).getAllDistrictName();

        this.mockMvc.perform(
                get(BASE_URI + "/names")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(getAllDistrictNameDocs());
    }

    @DisplayName("전체 Zone 가져오기 문서화")
    @Test
    void getAllZone() throws Exception {
        ZoneDto zoneDto = ZoneDto.builder()
                .zoneId(1L)
                .districtId(1L)
                .districtName("서울 송파구")
                .topLeftLatitude("37.542124")
                .topLeftLongitude("127.069733")
                .bottomRightLatitude("37.505974")
                .bottomRightLongitude("127.069733")
                .build();
        List<ZoneDto> zoneDtos = Arrays.asList(zoneDto, zoneDto);

        when(districtQueryService.getAllZone()).thenReturn(zoneDtos);

        this.mockMvc.perform(
                get(BASE_URI + "/zones")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(getAllZoneDocs());
    }
}
