package com.example.demo.domain.file.controller;

import com.example.demo.global.dto.ApiResponse;
import com.example.demo.global.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final S3Service s3Service;

    @PostMapping("/upload/profile")
    public ResponseEntity<ApiResponse<String>> uploadProfileImage(@RequestParam("file") MultipartFile file) {
        String imageUrl = s3Service.upload(file, "profile");
        return ResponseEntity.ok(ApiResponse.success("프로필 이미지가 성공적으로 업로드되었습니다.", imageUrl));
    }

    @PostMapping("/upload/favorite")
    public ResponseEntity<ApiResponse<String>> uploadFavoriteImage(@RequestParam("file") MultipartFile file) {
        String imageUrl = s3Service.upload(file, "favorite");
        return ResponseEntity.ok(ApiResponse.success("최애 이미지가 성공적으로 업로드되었습니다.", imageUrl));
    }
} 