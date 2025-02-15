package com.example.demo.domain.favorite.controller;

import com.example.demo.domain.favorite.service.FavoriteService;
import com.example.demo.domain.member.dto.ProfileResponse;
import com.example.demo.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    /**
     * 찜하기
     * POST /api/favorites/{toMemberId}
     */
    @PostMapping("/{toMemberId}")
    public ResponseEntity<ApiResponse<String>> addFavorite(
            @RequestHeader("X-Member-Id") String fromMemberId,
            @PathVariable String toMemberId) {
        favoriteService.addFavorite(fromMemberId, toMemberId);
        return ResponseEntity.ok(ApiResponse.success("찜하기가 완료되었습니다."));
    }

    /**
     * 찜 취소
     * DELETE /api/favorites/{toMemberId}
     */
    @DeleteMapping("/{toMemberId}")
    public ResponseEntity<ApiResponse<String>> removeFavorite(
            @RequestHeader("X-Member-Id") String fromMemberId,
            @PathVariable String toMemberId) {
        favoriteService.removeFavorite(fromMemberId, toMemberId);
        return ResponseEntity.ok(ApiResponse.success("찜하기가 취소되었습니다."));
    }

    /**
     * 내가 찜한 목록 조회
     * GET /api/favorites/to-me
     */
    @GetMapping("/to-me")
    public ResponseEntity<ApiResponse<List<ProfileResponse>>> getFavoriteMembers(
            @RequestHeader("X-Member-Id") String memberId) {
        List<ProfileResponse> favorites = favoriteService.getFavoriteMembers(memberId);
        return ResponseEntity.ok(ApiResponse.success(favorites));
    }

    /**
     * 나를 찜한 목록 조회
     * GET /api/favorites/from-me
     */
    @GetMapping("/from-me")
    public ResponseEntity<ApiResponse<List<ProfileResponse>>> getFavoritedByMembers(
            @RequestHeader("X-Member-Id") String memberId) {
        List<ProfileResponse> favorites = favoriteService.getFavoritedByMembers(memberId);
        return ResponseEntity.ok(ApiResponse.success(favorites));
    }

    /**
     * 찜 여부 확인
     * GET /api/favorites/check/{toMemberId}
     */
    @GetMapping("/check/{toMemberId}")
    public ResponseEntity<ApiResponse<Boolean>> checkFavorite(
            @RequestHeader("X-Member-Id") String fromMemberId,
            @PathVariable String toMemberId) {
        boolean isFavorite = favoriteService.isFavorite(fromMemberId, toMemberId);
        return ResponseEntity.ok(ApiResponse.success(isFavorite));
    }
} 