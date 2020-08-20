package com.songpapeople.hashtagmap.taglevel.api;

import com.songpapeople.hashtagmap.taglevel.service.TagLevelCommandService;
import com.songpapeople.hashtagmap.taglevel.service.TagLevelQueryService;
import com.songpapeople.hashtagmap.taglevel.service.dto.TagLevelDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static com.songpapeople.hashtagmap.docs.taglevel.TagLevelDocumentation.getDocumentByGetTagLevels;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// todo ascii 추가
@WebMvcTest(TagLevelApiController.class)
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureMockMvc
class TagLevelApiControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private TagLevelCommandService tagLevelCommandService;

    @MockBean
    private TagLevelQueryService tagLevelQueryService;

    @BeforeEach
    private void setUp(WebApplicationContext webApplicationContext,
                       RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @DisplayName("TagLevel 정보를 조회한다.")
    @Test
    public void findTagLevelsTest() throws Exception {
        when(tagLevelQueryService.findAll()).thenReturn(
                Arrays.asList(
                        new TagLevelDto(1L, 100L, 200L),
                        new TagLevelDto(2L, 201L, 300L)
                )
        );
        mockMvc.perform(get("/tag-levels"))
                .andExpect(status().isOk())
                .andDo(getDocumentByGetTagLevels());
    }
}