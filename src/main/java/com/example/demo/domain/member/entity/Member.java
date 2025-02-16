package com.example.demo.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @Column(length = 50)
    private String id;  // Firebase UUID를 저장

    @Column(nullable = false, unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender preferredGender;  // 선호하는 성별

    @Column(nullable = false)
    private Integer height;

    @ElementCollection
    @CollectionTable(name = "member_hobbies", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "hobby")
    private List<String> hobbies;  // 취미 리스트

    private String job;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer age;

    private String introduction;

    private String mbti;

    @ElementCollection
    @CollectionTable(name = "member_favorite_images", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "image_url")
    private List<String> favoriteImages;  // 최애 이미지 목록

    private String profileImage;  // 프로필 이미지

    public enum Gender {
        MALE, FEMALE
    }

    // 회원 정보 수정 메서드
    public void updateProfile(String nickname, Gender gender, Gender preferredGender,
                            Integer height, List<String> hobbies, String job,
                            String location, String introduction, String mbti) {
        if (nickname != null) this.nickname = nickname;
        if (gender != null) this.gender = gender;
        if (preferredGender != null) this.preferredGender = preferredGender;
        if (height != null) this.height = height;
        if (hobbies != null) this.hobbies = hobbies;
        if (job != null) this.job = job;
        if (location != null) this.location = location;
        if (introduction != null) this.introduction = introduction;
        if (mbti != null) this.mbti = mbti;
    }

    public void updateProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void updateFavoriteImages(List<String> favoriteImages) {
        this.favoriteImages = favoriteImages;
    }

    public void addFavoriteImage(String favoriteImage) {
        if (this.favoriteImages == null) {
            this.favoriteImages = new ArrayList<>();
        }
        this.favoriteImages.add(favoriteImage);
    }
} 