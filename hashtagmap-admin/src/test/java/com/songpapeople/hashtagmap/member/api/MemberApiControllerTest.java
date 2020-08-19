package com.songpapeople.hashtagmap.member.api;

import com.songpapeople.hashtagmap.docs.member.MemberApiDocumentation;
import com.songpapeople.hashtagmap.member.service.AdminMemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberApiController.class)
class MemberApiControllerTest extends MemberApiDocumentation {
    @MockBean
    private AdminMemberService adminMemberService;

    @DisplayName("login 요청 controller 테스트")
    @Test
    void login() throws Exception {
        doNothing().when(adminMemberService).validateMember(any());

        String content = "{\"nickName\":\"dev_hakseong\",\"password\":\"thdvkrntkfkaemf\"}";
        mockMvc.perform(post("/admin-member/login")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(getDocumentByAdminMemberLogin());
    }
}
