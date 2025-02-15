package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.dto.ProfileResponse;
import com.example.demo.domain.member.dto.SignUpRequest;
import com.example.demo.domain.member.dto.UpdateProfileRequest;
import com.example.demo.domain.member.entity.Member.Gender;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 API
     * POST /api/members/signup
     */
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signUp(@RequestBody @Valid SignUpRequest request) {
        String memberId = memberService.signUp(request);
        return ResponseEntity.ok(ApiResponse.success("회원가입이 완료되었습니다. 회원님의 UUID는 " + memberId + " 입니다.", memberId));
    }

    /**
     * ID로 프로필 조회 API
     * GET /api/members/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfile(@PathVariable String id) {
        ProfileResponse profile = memberService.getProfile(id);
        return ResponseEntity.ok(ApiResponse.success(profile));
    }

    /**
     * 닉네임으로 프로필 조회 API
     * GET /api/members/profile/{nickname}
     */
    @GetMapping("/profile/{nickname}")
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfileByNickname(@PathVariable String nickname) {
        ProfileResponse profile = memberService.getProfileByNickname(nickname);
        return ResponseEntity.ok(ApiResponse.success(profile));
    }

    /**
     * 전체 회원 프로필 조회 API
     * GET /api/members
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProfileResponse>>> getAllProfiles() {
        List<ProfileResponse> profiles = memberService.getAllProfiles();
        return ResponseEntity.ok(ApiResponse.success(profiles));
    }

    /**
     * 성별로 회원 프로필 조회 API
     * GET /api/members/gender/{gender}
     * gender: MALE 또는 FEMALE
     */
    @GetMapping("/gender/{gender}")
    public ResponseEntity<ApiResponse<List<ProfileResponse>>> getProfilesByGender(@PathVariable Gender gender) {
        List<ProfileResponse> profiles = memberService.getProfilesByGender(gender);
        return ResponseEntity.ok(ApiResponse.success(profiles));
    }

    /**
     * 프로필 수정 API
     * PUT /api/members/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfileResponse>> updateProfile(
            @PathVariable String id,
            @RequestBody @Valid UpdateProfileRequest request) {
        ProfileResponse updatedProfile = memberService.updateProfile(id, request);
        return ResponseEntity.ok(ApiResponse.success("프로필이 성공적으로 수정되었습니다.", updatedProfile));
    }
}