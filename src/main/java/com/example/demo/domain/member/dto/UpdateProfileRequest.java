package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.entity.Member.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest {
    private String nickname;
    private Gender gender;
    private Gender preferredGender;
    private Integer height;
    private List<String> hobbies;
    private String job;
    private String location;
    private String introduction;
    private String mbti;
    private List<String> favoriteImageUrls;
    private String profileImageUrl;
} 