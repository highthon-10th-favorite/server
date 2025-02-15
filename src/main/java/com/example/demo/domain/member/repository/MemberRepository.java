package com.example.demo.domain.member.repository;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.Member.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByNickname(String nickname);
    boolean existsByNickname(String nickname);
    List<Member> findAllByGender(Gender gender);
} 