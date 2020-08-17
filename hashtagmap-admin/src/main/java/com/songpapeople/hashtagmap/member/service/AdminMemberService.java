package com.songpapeople.hashtagmap.member.service;

import com.songpapeople.hashtagmap.exception.AdminException;
import com.songpapeople.hashtagmap.exception.AdminExceptionStatus;
import com.songpapeople.hashtagmap.member.dto.LoginRequest;
import com.songpapeople.hashtagmap.member.model.AdminMember;
import com.songpapeople.hashtagmap.member.repository.AdminMemberRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {
    private final AdminMemberRepository adminMemberRepository;

    public AdminMemberService(AdminMemberRepository adminMemberRepository) {
        this.adminMemberRepository = adminMemberRepository;
    }

    public void validate(LoginRequest loginRequest) {
        AdminMember member = adminMemberRepository.findByNickName(loginRequest.getNickName())
                .orElseThrow(() -> new AdminException(AdminExceptionStatus.NOT_FOUNT_NICK_NAME,
                        loginRequest.getNickName() + "는 존재하지 않습니다."));
        if (member.isNotMatchPassword(loginRequest.getPassword())) {
            throw new AdminException(AdminExceptionStatus.WRONG_PASSWORD,
                    "비밀번호가 일치하지 않습니다.");
        }
    }
}
