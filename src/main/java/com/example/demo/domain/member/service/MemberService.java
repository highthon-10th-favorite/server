package com.example.demo.domain.member.service;

import com.example.demo.domain.favorite.entity.Favorite;
import com.example.demo.domain.favorite.repository.FavoriteRepository;
import com.example.demo.domain.member.dto.ProfileResponse;
import com.example.demo.domain.member.dto.SignUpRequest;
import com.example.demo.domain.member.dto.UpdateProfileRequest;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.Member.Gender;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.global.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final S3Service s3Service;

    @Transactional
    public String signUp(SignUpRequest request) {
        // 닉네임 중복 검사
        if (memberRepository.existsByNickname(request.getNickname())) {
            throw new RuntimeException("이미 존재하는 닉네임입니다.");
        }

        // ID 중복 검사
        if (memberRepository.existsById(request.getId())) {
            throw new RuntimeException("이미 가입된 회원입니다.");
        }

        // 회원 저장
        Member member = request.toEntity();
        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

    @Transactional
    public ProfileResponse updateProfile(String id, UpdateProfileRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        // 닉네임이 변경되었고, 새로운 닉네임이 이미 존재하는 경우
        if (!member.getNickname().equals(request.getNickname()) && 
            memberRepository.existsByNickname(request.getNickname())) {
            throw new RuntimeException("이미 존재하는 닉네임입니다.");
        }

        // 프로필 업데이트
        member.updateProfile(
            request.getNickname(),
            request.getGender(),
            request.getPreferredGender(),
            request.getHeight(),
            request.getHobbies(),
            request.getJob(),
            request.getLocation(),
            request.getIntroduction(),
            request.getMbti()
        );

        // 이미지 URL 업데이트
        if (request.getProfileImageUrl() != null) {
            member.updateProfileImage(request.getProfileImageUrl());
        }
        if (request.getFavoriteImageUrls() != null) {
            member.updateFavoriteImages(request.getFavoriteImageUrls());
        }

        return ProfileResponse.from(member);
    }

    public ProfileResponse getProfile(String id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
        return ProfileResponse.from(member);
    }

    public ProfileResponse getProfileByNickname(String nickname) {
        Member member = memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
        return ProfileResponse.from(member);
    }

    public List<ProfileResponse> getAllProfiles() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(ProfileResponse::from)
                .collect(Collectors.toList());
    }

    // 성별로 회원 조회
    public List<ProfileResponse> getProfilesByGender(Gender gender) {
        List<Member> members = memberRepository.findAllByGender(gender);
        return members.stream()
                .map(ProfileResponse::from)
                .collect(Collectors.toList());
    }

    private String uploadImage(MultipartFile file, String directory) {
        return s3Service.upload(file, directory);
    }
} 