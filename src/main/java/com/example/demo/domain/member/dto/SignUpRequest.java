package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "ID는 필수입니다.")
    private String id;  // Firebase UUID

    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;

    @NotNull(message = "성별은 필수입니다.")
    private Member.Gender gender;

    @NotNull(message = "선호하는 성별은 필수입니다.")
    private Member.Gender preferredGender;

    @NotNull(message = "키는 필수입니다.")
    private Integer height;

    private List<String> hobbies;

    private String job;

    @NotBlank(message = "사는 곳은 필수입니다.")
    private String location;

    @NotNull(message = "나이는 필수입니다.")
    private Integer age;

    private String introduction;

    private String mbti;

    private List<String> favoriteImageUrls;

    private String profileImageUrl;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .nickname(nickname)
                .gender(gender)
                .preferredGender(preferredGender)
                .height(height)
                .hobbies(hobbies)
                .job(job)
                .location(location)
                .age(age)
                .introduction(introduction)
                .mbti(mbti)
                .favoriteImages(favoriteImageUrls)
                .profileImage(profileImageUrl)
                .build();
    }
} 