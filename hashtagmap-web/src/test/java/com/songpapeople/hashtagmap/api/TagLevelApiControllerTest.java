package com.songpapeople.hashtagmap.api;

import com.songpapeople.hashtagmap.docs.TagLevelApiDocumentation;
import com.songpapeople.hashtagmap.dto.TagLevelResponse;
import com.songpapeople.hashtagmap.service.TagLevelQueryService;
import com.songpapeople.hashtagmap.taglevel.model.TagLevel;
import com.songpapeople.hashtagmap.taglevel.model.TagLevels;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TagLevelApiController.class)
public class TagLevelApiControllerTest extends TagLevelApiDocumentation {
    @MockBean
    private TagLevelQueryService tagLevelQueryService;

    private List<TagLevelResponse> tagLevelResponses;

    @BeforeEach
    private void setUp() {
        TagLevels tagLevels = new TagLevels(Arrays.asList(
                new TagLevel(1L, 100L, 105L),
                new TagLevel(2L, 106L, 110L),
                new TagLevel(3L, 111L, 115L),
                new TagLevel(4L, 116L, 120L),
                new TagLevel(5L, 121L, 125L)
        ));
        tagLevelResponses = TagLevelResponse.of(tagLevels);
    }

    @DisplayName("데이터베이스에 저장되어 있는 태그 레벨 정보를 가져오는지 테스트")
    @Test
    void findAllTagLevelsTest() throws Exception {
        given(tagLevelQueryService.findTagLevels()).willReturn(tagLevelResponses);

        mockMvc.perform(get("/tag-levels")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", Matchers.hasSize(5)))
                .andExpect(jsonPath("$.data[0].minHashtagCount", Matchers.is(100)))
                .andExpect(jsonPath("$.data[0].maxHashtagCount", Matchers.is(105)))
                .andExpect(jsonPath("$.data[1].minHashtagCount", Matchers.is(106)))
                .andExpect(jsonPath("$.data[1].maxHashtagCount", Matchers.is(110)))
                .andExpect(jsonPath("$.data[2].minHashtagCount", Matchers.is(111)))
                .andExpect(jsonPath("$.data[2].maxHashtagCount", Matchers.is(115)))
                .andExpect(jsonPath("$.data[3].minHashtagCount", Matchers.is(116)))
                .andExpect(jsonPath("$.data[3].maxHashtagCount", Matchers.is(120)))
                .andExpect(jsonPath("$.data[4].minHashtagCount", Matchers.is(121)))
                .andExpect(jsonPath("$.data[4].maxHashtagCount", Matchers.is(125)))
                .andDo(getDocumentByFindTagLevels());
    }
}
