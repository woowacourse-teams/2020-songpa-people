package com.songpapeople.hashtagmap.api;

import com.songpapeople.hashtagmap.doc.InstagramApiDocumentation;
import com.songpapeople.hashtagmap.dto.InstagramPost.InstagramPostResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InstagramApiControllerTest extends InstagramApiDocumentation {
    @DisplayName("인스타그램post요청 controller 테스트")
    @Test
    void getInstagramPost() throws Exception {
        List<InstagramPostResponse> instagramPostResponses = Arrays.asList(
                new InstagramPostResponse(1L, "imageUrl1", "postUrl1"),
                new InstagramPostResponse(2L, "imageUrl2", "postUrl2"),
                new InstagramPostResponse(3L, "imageUrl3", "postUrl3"),
                new InstagramPostResponse(4L, "imageUrl4", "postUrl4"),
                new InstagramPostResponse(5L, "imageUrl5", "postUrl5"),
                new InstagramPostResponse(6L, "imageUrl6", "postUrl6"),
                new InstagramPostResponse(7L, "imageUrl7", "postUrl7"),
                new InstagramPostResponse(8L, "imageUrl8", "postUrl8"),
                new InstagramPostResponse(9L, "imageUrl9", "postUrl9")
        );

        given(instagramPostQueryService.findAllByInstagramId(any()))
                .willReturn(instagramPostResponses);

        mockMvc.perform(get("/instagram/1/post"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", Matchers.hasSize(9)))
                .andExpect(jsonPath("$.data[0].id", Matchers.instanceOf(Integer.class)))
                .andExpect(jsonPath("$.data[0].imageUrl", Matchers.instanceOf(String.class)))
                .andExpect(jsonPath("$.data[0].postUrl", Matchers.instanceOf(String.class)))
                .andDo(getDocumentByGetInstagramPost());
    }
}
