package com.songpapeople.hashtagmap.member.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AdminMemberTest {

    @DisplayName("패스워드가 일치하지 않는 경우")
    @Test
    void isNotMatchPassword() {
        AdminMember adminMember = new AdminMember("nickName", "password");

        assertThat(adminMember.isNotMatchPassword("fail_password")).isTrue();
    }
}