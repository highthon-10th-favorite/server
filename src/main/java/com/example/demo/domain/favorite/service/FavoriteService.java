package com.example.demo.domain.favorite.service;

import com.example.demo.domain.favorite.entity.Favorite;
import com.example.demo.domain.favorite.repository.FavoriteRepository;
import com.example.demo.domain.member.dto.ProfileResponse;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final MemberRepository memberRepository;

    // 찜하기
    @Transactional
    public void addFavorite(String fromMemberId, String toMemberId) {
        // 자기 자신을 찜할 수 없음
        if (fromMemberId.equals(toMemberId)) {
            throw new RuntimeException("자기 자신을 찜할 수 없습니다.");
        }

        Member fromMember = memberRepository.findById(fromMemberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
        Member toMember = memberRepository.findById(toMemberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        // 이미 찜한 경우 체크
        if (favoriteRepository.findByFromMemberAndToMember(fromMember, toMember).isPresent()) {
            throw new RuntimeException("이미 찜한 회원입니다.");
        }

        Favorite favorite = Favorite.builder()
                .fromMember(fromMember)
                .toMember(toMember)
                .build();

        favoriteRepository.save(favorite);
    }

    // 찜 취소
    @Transactional
    public void removeFavorite(String fromMemberId, String toMemberId) {
        Member fromMember = memberRepository.findById(fromMemberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
        Member toMember = memberRepository.findById(toMemberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        favoriteRepository.deleteByFromMemberAndToMember(fromMember, toMember);
    }

    // 내가 찜한 목록 조회
    public List<ProfileResponse> getFavoriteMembers(String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        return favoriteRepository.findAllByFromMember(member).stream()
                .map(favorite -> ProfileResponse.from(favorite.getToMember()))
                .collect(Collectors.toList());
    }

    // 나를 찜한 목록 조회
    public List<ProfileResponse> getFavoritedByMembers(String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        return favoriteRepository.findAllByToMember(member).stream()
                .map(favorite -> ProfileResponse.from(favorite.getFromMember()))
                .collect(Collectors.toList());
    }

    // 찜 여부 확인
    public boolean isFavorite(String fromMemberId, String toMemberId) {
        Member fromMember = memberRepository.findById(fromMemberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
        Member toMember = memberRepository.findById(toMemberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        return favoriteRepository.findByFromMemberAndToMember(fromMember, toMember).isPresent();
    }
} 