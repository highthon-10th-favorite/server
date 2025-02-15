package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProfileResponse {
    private String nickname;
    private Member.Gender gender;
    private Member.Gender preferredGender;
    private Integer height;
    private List<String> hobbies;
    private String job;
    private String location;
    private Integer age;
    private String introduction;
    private String mbti;
    private String favoriteImageUrl;
    private String profileImageUrl;

    public static ProfileResponse from(Member member) {
        return ProfileResponse.builder()
                .nickname(member.getNickname())
                .gender(member.getGender())
                .preferredGender(member.getPreferredGender())
                .height(member.getHeight())
                .hobbies(member.getHobbies())
                .job(member.getJob())
                .location(member.getLocation())
                .age(member.getAge())
                .introduction(member.getIntroduction())
                .mbti(member.getMbti())
                .favoriteImageUrl(member.getFavoriteImage())
                .profileImageUrl(member.getProfileImage())
                .build();
    }
} 