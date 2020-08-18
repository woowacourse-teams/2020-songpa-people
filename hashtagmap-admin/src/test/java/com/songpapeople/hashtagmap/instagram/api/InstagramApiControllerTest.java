package com.songpapeople.hashtagmap.instagram.api;

import com.songpapeople.hashtagmap.scheduler.InstagramScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.songpapeople.hashtagmap.docs.instagram.InstagramApiDocumentation.getDocumentByUpdate;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(controllers = InstagramSchedulerApiController.class)
@AutoConfigureMockMvc
public class InstagramApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InstagramScheduler instagramScheduler;

    @BeforeEach
    private void setUp(WebApplicationContext webApplicationContext,
                       RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @DisplayName("Instagram Scheduler를 시작")
    @Test
    void updateTest() throws Exception {
        doNothing().when(instagramScheduler).update();

        mockMvc.perform(put("/instagram-scheduler"))
                .andExpect(status().isOk())
                .andDo(getDocumentByUpdate());
    }
}
