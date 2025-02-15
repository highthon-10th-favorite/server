package com.example.demo.domain.favorite.repository;

import com.example.demo.domain.favorite.entity.Favorite;
import com.example.demo.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // 이미 찜했는지 확인
    Optional<Favorite> findByFromMemberAndToMember(Member fromMember, Member toMember);
    
    // 내가 찜한 목록 조회
    List<Favorite> findAllByFromMember(Member fromMember);
    
    // 나를 찜한 목록 조회
    List<Favorite> findAllByToMember(Member toMember);
    
    // 찜 삭제
    void deleteByFromMemberAndToMember(Member fromMember, Member toMember);
} 