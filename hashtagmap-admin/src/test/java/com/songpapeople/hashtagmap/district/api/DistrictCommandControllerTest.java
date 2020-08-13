package com.songpapeople.hashtagmap.district.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songpapeople.hashtagmap.district.service.DistrictCommandService;
import com.songpapeople.hashtagmap.district.service.dto.DistrictDeleteDto;
import com.songpapeople.hashtagmap.district.service.dto.DistrictSaveDto;
import com.songpapeople.hashtagmap.district.service.dto.DistrictUpdateDto;
import com.songpapeople.hashtagmap.district.service.dto.ZoneDeleteDto;
import com.songpapeople.hashtagmap.district.service.dto.ZoneSaveDto;
import com.songpapeople.hashtagmap.district.service.dto.ZoneUpdateDto;
import com.songpapeople.hashtagmap.docs.ApiDocument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Arrays;

import static com.songpapeople.hashtagmap.docs.district.DistrictCommandDocument.deleteZonesDocs;
import static com.songpapeople.hashtagmap.docs.district.DistrictCommandDocument.emptyDocs;
import static com.songpapeople.hashtagmap.docs.district.DistrictCommandDocument.saveDistrictDocs;
import static com.songpapeople.hashtagmap.docs.district.DistrictCommandDocument.saveZoneDocs;
import static com.songpapeople.hashtagmap.docs.district.DistrictCommandDocument.updateZoneDocs;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DistrictCommandController.class)
@ExtendWith({MockitoExtension.class})
public class DistrictCommandControllerTest extends ApiDocument {
    private static final String BASE_URI = "/districts";

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DistrictCommandService districtCommandService;

    @DisplayName("새로운 District 저장 문서화")
    @Test
    void saveDistrict() throws Exception {
        doReturn(1L).when(districtCommandService).saveDistrict(any());

        DistrictSaveDto districtSaveDto = new DistrictSaveDto("서울 송파구");
        String content = objectMapper.writeValueAsString(districtSaveDto);
        this.mockMvc.perform(
                post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(saveDistrictDocs());
    }

    @DisplayName("선택한 District 지우기 문서화")
    @Test
    void deleteDistricts() throws Exception {
        //given
        doNothing().when(districtCommandService).deleteDistricts(any());

        DistrictDeleteDto districtDeleteDto = new DistrictDeleteDto(Arrays.asList(1L, 2L));
        String content = objectMapper.writeValueAsString(districtDeleteDto);

        this.mockMvc.perform(
                delete(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(emptyDocs("district/deleteDistricts"));
    }

    @DisplayName("선택한 District 수정하기 문서화")
    @Test
    void updateDistrict() throws Exception {
        //given
        doNothing().when(districtCommandService).updateDistrict(any());

        DistrictUpdateDto districtUpdateDto = new DistrictUpdateDto(1L, "수정");
        String content = objectMapper.writeValueAsString(districtUpdateDto);

        this.mockMvc.perform(
                patch(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(emptyDocs("district/updateDistrict"));
    }

    @DisplayName("새로운 Zone 생성하기 문서화")
    @Test
    void saveZone() throws Exception {
        when(districtCommandService.saveZone(any())).thenReturn(1L);

        ZoneSaveDto zoneSaveDto = ZoneSaveDto.builder()
                .districtName("서울 송파구")
                .topLeftLatitude("37.542124")
                .topLeftLongitude("127.069733")
                .bottomRightLatitude("37.505974")
                .bottomRightLongitude("127.069733")
                .build();
        String content = objectMapper.writeValueAsString(zoneSaveDto);

        this.mockMvc.perform(
                post(BASE_URI + "/zones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(saveZoneDocs());
    }

    @DisplayName("기존 Zone 수정하기 문서화")
    @Test
    void updateZone() throws Exception {
        doNothing().when(districtCommandService).updateZone(any());

        ZoneUpdateDto zoneUpdateDto = ZoneUpdateDto.builder()
                .zoneId(1L)
                .districtName("서울 송파구")
                .topLeftLatitude("37.542124")
                .topLeftLongitude("127.069733")
                .bottomRightLatitude("37.505974")
                .bottomRightLongitude("127.069733")
                .build();
        String content = objectMapper.writeValueAsString(zoneUpdateDto);

        this.mockMvc.perform(
                patch(BASE_URI + "/zones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(updateZoneDocs());
    }

    @DisplayName("Zone 제거 문서화")
    @Test
    void deleteZones() throws Exception {
        ZoneDeleteDto zoneDeleteDto = new ZoneDeleteDto(Arrays.asList(1L, 2L));
        String content = objectMapper.writeValueAsString(zoneDeleteDto);

        this.mockMvc.perform(
                delete(BASE_URI + "/zones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(deleteZonesDocs());
    }
}
