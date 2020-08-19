package com.songpapeople.hashtagmap.blacklist.api;

import com.songpapeople.hashtagmap.blacklist.service.dto.SubBlackListDto;
import com.songpapeople.hashtagmap.docs.blacklist.BlackListApiDocumentation;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.service.InstagramQueryService;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BlackListApiController.class)
class BlackListApiControllerTest extends BlackListApiDocumentation {
    @MockBean
    private InstagramQueryService instagramQueryService;

    @DisplayName("해시태그수가 많은 subBlackList 목록 요청")
    @Test
    void getSubBlackList() throws Exception {
        Place place = Place.builder()
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
}
