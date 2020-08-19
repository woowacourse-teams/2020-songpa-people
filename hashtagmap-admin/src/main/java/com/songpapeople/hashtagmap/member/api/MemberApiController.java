package com.songpapeople.hashtagmap.member.api;

import com.songpapeople.hashtagmap.member.service.AdminMemberService;
import com.songpapeople.hashtagmap.member.service.dto.LoginRequest;
import com.songpapeople.hashtagmap.response.CustomResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin-member")
public class MemberApiController {
    private final AdminMemberService adminMemberService;

    public MemberApiController(AdminMemberService adminMemberService) {
        this.adminMemberService = adminMemberService;
    }

    @PostMapping
    @RequestMapping("/login")
    public CustomResponse<Void> adminLogin(@RequestBody LoginRequest loginRequest) {
        adminMemberService.validateMember(loginRequest);
        return CustomResponse.empty();
    }
}
