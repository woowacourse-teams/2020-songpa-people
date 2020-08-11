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
                new InstagramPostResponse("imageUrl1", "postUrl1"),
                new InstagramPostResponse("imageUrl2", "postUrl2"),
                new InstagramPostResponse("imageUrl3", "postUrl3"),
                new InstagramPostResponse("imageUrl4", "postUrl4"),
                new InstagramPostResponse("imageUrl5", "postUrl5"),
                new InstagramPostResponse("imageUrl6", "postUrl6"),
                new InstagramPostResponse("imageUrl7", "postUrl7"),
                new InstagramPostResponse("imageUrl8", "postUrl8"),
                new InstagramPostResponse("imageUrl9", "postUrl9")
        );

        given(instagramPostQueryService.findAllByInstagramId(any()))
                .willReturn(instagramPostResponses);

        mockMvc.perform(get("/instagram/1/post"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", Matchers.hasSize(9)))
                .andExpect(jsonPath("$.data[0].imageUrl", Matchers.instanceOf(String.class)))
                .andExpect(jsonPath("$.data[0].postUrl", Matchers.instanceOf(String.class)))
                .andDo(getDocumentByGetInstagramPost());
    }
}
