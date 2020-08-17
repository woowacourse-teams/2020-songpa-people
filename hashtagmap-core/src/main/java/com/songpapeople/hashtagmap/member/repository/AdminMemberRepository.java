package com.songpapeople.hashtagmap.member.repository;

import com.songpapeople.hashtagmap.member.model.AdminMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminMemberRepository extends JpaRepository<AdminMember, Long> {
    Optional<AdminMember> findByNickName(String nickName);
}
