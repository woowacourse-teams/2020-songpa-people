package com.songpapeople.hashtagmap.member.service;

import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.member.dto.LoginRequest;
import com.songpapeople.hashtagmap.member.model.AdminMember;
import com.songpapeople.hashtagmap.member.repository.AdminMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class AdminMemberServiceTest {
    @Autowired
    private AdminMemberRepository adminMemberRepository;

    @Autowired
    private AdminMemberService adminMemberService;

    @BeforeEach
    void setUp() {
        AdminMember member = new AdminMember("hakseong", "1234");
        adminMemberRepository.save(member);
    }

    @DisplayName("로그인 / 존재하지 않는 아이디 예외처리 테스트")
    @Test
    void loginWithError1() {
        LoginRequest loginRequest = new LoginRequest("bebop", "1234");

        assertThatThrownBy(() -> adminMemberService.validate(loginRequest))
                .isInstanceOf(AdminException.class)
                .hasMessage(loginRequest.getNickName() + "는 존재하지 않습니다.");
    }

    @DisplayName("로그인 / 비밀번호가 틀렸을 때 예외처리 테스트")
    @Test
    void loginWithError2() {
        LoginRequest loginRequest = new LoginRequest("hakseong", "0000");

        assertThatThrownBy(() -> adminMemberService.validate(loginRequest))
                .isInstanceOf(AdminException.class)
                .hasMessage("비밀번호가 일치하지 않습니다.");
    }

    @AfterEach
    void tearDown() {
        adminMemberRepository.deleteAll();
    }
}
