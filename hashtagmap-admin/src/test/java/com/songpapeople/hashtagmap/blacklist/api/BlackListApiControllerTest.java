package com.songpapeople.hashtagmap.blacklist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songpapeople.hashtagmap.blacklist.service.BlackListCommandService;
import com.songpapeople.hashtagmap.blacklist.service.dto.BlackListAddRequest;
import com.songpapeople.hashtagmap.blacklist.service.dto.SubBlackListDto;
import com.songpapeople.hashtagmap.docs.blacklist.BlackListApiDocumentation;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.service.InstagramCommandService;
import com.songpapeople.hashtagmap.instagram.service.InstagramQueryService;
import com.songpapeople.hashtagmap.place.domain.model.Category;
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
    private BlackListCommandService blackListCommandService;

    @MockBean
    private InstagramScheduleService instagramScheduleService;


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
        List<SubBlackListDto> subBlackListDtos = new ArrayList<>();
        subBlackListDtos.add(SubBlackListDto.of(instagram));

        when(instagramQueryService.findSubBlackListInstagram()).thenReturn(subBlackListDtos);

        mockMvc.perform(get("/blacklist/sub"))
                .andExpect(status().isOk())
                .andDo(getDocumentByGetSubBlackList());
    }

    @DisplayName("블랙리스트 추가하고 instagram을 대체어로 업데이트 시키는 요청 테스트")
    @Test
    void addBlackList() throws Exception {
        when(blackListCommandService.save(any())).thenReturn(null);
        when(instagramQueryService.findByPlaceId(any())).thenReturn(null);
        when(instagramScheduleService.findHashtagCount(any())).thenReturn(null);
        when(instagramCommandService.update(any(), any(), any())).thenReturn(
                Instagram.builder()
                        .id(1L)
                        .place(Place.builder()
                                .id(1L)
                                .placeName("place")
                                .kakaoId("1")
                                .category(Category.RESTAURANT)
                                .placeUrl("placeUrl")
                                .location(new Location(new Point("40","130"),"address"))
                                .build())
                        .hashtagName("newName")
                        .hashtagCount(12512L)
                        .build()
        );

        BlackListAddRequest blackListAddRequest = new BlackListAddRequest(1L, "newName");

        mockMvc.perform(post("/blacklist")
                .content(objectMapper.writeValueAsString(blackListAddRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(getDocumentByPostBlackList());
    }
}
