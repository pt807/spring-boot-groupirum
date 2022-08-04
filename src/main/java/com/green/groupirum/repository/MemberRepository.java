package com.green.groupirum.repository;

import com.green.groupirum.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    long countBySocialIgnoreCase(String social);

    boolean existsByNickname(String nickname);

    Optional<Member> findByNickname(String nickname);
}
