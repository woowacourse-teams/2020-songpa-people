package com.songpapeople.hashtagmap.blacklist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songpapeople.hashtagmap.blacklist.service.BlackListCommandService;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListAddRequest;
import com.songpapeople.hashtagmap.blacklist.service.dto.SemiBlackListDto;
import com.songpapeople.hashtagmap.docs.blacklist.BlackListApiDocumentation;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.service.InstagramCommandService;
import com.songpapeople.hashtagmap.instagram.service.InstagramQueryService;
import com.songpapeople.hashtagmap.instagrampost.service.InstagramPostCommandService;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.scheduler.InstagramScheduleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BlackListApiController.class)
class BlackListApiControllerTest extends BlackListApiDocumentation {
    @MockBean
    private InstagramQueryService instagramQueryService;

    @MockBean
    private InstagramCommandService instagramCommandService;

    @MockBean
    private InstagramPostCommandService instagramPostCommandService;

    @MockBean
    private InstagramScheduleService instagramScheduleService;

    @MockBean
    private BlackListCommandService blackListCommandService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("해시태그수가 많은 subBlackList 목록 요청")
    @Test
    void getSubBlackList() throws Exception {
        Place place = Place.builder()
                .id(1L)
                .placeName("place")
                .location(new Location(new Point("40", "130"), "address"))
                .build();
        Instagram instagram = Instagram.builder()
                .place(place)
                .hashtagCount(10000L)
                .hashtagName(place.getPlaceName())
                .build();
        List<SemiBlackListDto> semiBlackListDtos = new ArrayList<>();
        semiBlackListDtos.add(SemiBlackListDto.of(instagram));

        when(instagramQueryService.findSemiBlackListInstagram()).thenReturn(semiBlackListDtos);

        mockMvc.perform(get("/blacklist/semi"))
                .andExpect(status().isOk())
                .andDo(getDocumentByGetSubBlackList());
    }

    @DisplayName("블랙리스트 추가하고 instagram을 대체어로 업데이트 시키는 요청 테스트")
    @Test
    void addBlackList() throws Exception {
        when(blackListCommandService.save(any())).thenReturn(null);
        when(instagramQueryService.findByPlaceId(any())).thenReturn(null);
        Place place = Place.builder()
                .id(1L)
                .build();
        when(instagramScheduleService.updateBlackLists(any(), any())).thenReturn(
                Instagram.builder()
                        .place(place)
                        .hashtagName("newName")
                        .hashtagCount(1000L)
                        .build()
        );

        BlackListAddRequest blackListAddRequest = new BlackListAddRequest(place.getId(), "newName");

        mockMvc.perform(post("/blacklist")
                .content(objectMapper.writeValueAsString(blackListAddRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(getDocumentByPostBlackList());
    }

    @DisplayName("대체어로 검색했을 때 인기없는 인스타그램을 삭제하는 요청")
    @Test
    void deleteInstagramAndPost() throws Exception {
        when(instagramQueryService.findByPlaceId(any())).thenReturn(Instagram.builder().build());
        doNothing().when(instagramPostCommandService).deleteByInstagramId(any());
        doNothing().when(instagramCommandService).delete(any());

        mockMvc.perform(delete("/blacklist/instagram?placeId=1234"))
                .andExpect(status().isOk())
                .andDo(getDocumentByDeleteInstagram());
    }
}
